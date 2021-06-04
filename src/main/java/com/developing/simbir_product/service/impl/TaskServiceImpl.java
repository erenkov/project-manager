package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.controller.Dto.TaskResponseDto;
import com.developing.simbir_product.entity.*;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.mappers.TaskMapper;
import com.developing.simbir_product.repository.TaskRepository;
import com.developing.simbir_product.service.*;
import com.developing.simbir_product.utils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class TaskServiceImpl implements TaskService {

    private final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReleaseService releaseService;

    @Autowired
    private UserTaskHistoryService userTaskHistoryService;

    @Autowired
    private TaskReleaseHistoryService taskReleaseHistoryService;

    @Autowired
    private TaskMapper taskMapper;

    @Transactional
    @Override
    public TaskResponseDto getById(UUID id) {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Task with ID = '%s' not found", id)));

        return taskMapper.taskEntityToDto(taskEntity);
    }

    @Transactional
    @Override
    public TaskEntity getTaskEntityById(String id) {
        UUID uuid = Converter.getUuidFromString(id);
        return taskRepository.findById(uuid).orElseThrow(
                () -> new NotFoundException(String.format("Task with ID = '%s' not found", id)));
    }

    @Transactional
    @Override
    public TaskResponseDto addTask(TaskRequestDto taskRequestDto) {
        if (taskRequestDto == null) {
            throw new IllegalArgumentException("Can't add empty task");
        }
        TaskEntity taskEntity = taskRepository.save(taskMapper.taskDtoToEntity(taskRequestDto));
        if (taskRequestDto.getRelease() != null) {
            ReleaseEntity releaseEntity = releaseService.getEntityById(UUID.fromString(taskRequestDto.getRelease()));
            TaskReleaseHistoryEntity taskReleaseHistoryEntity = new TaskReleaseHistoryEntity();
            taskReleaseHistoryEntity.setTaskId(taskEntity);
            taskReleaseHistoryEntity.setReleaseId(releaseEntity);
            taskReleaseHistoryService.addTaskRelease(taskReleaseHistoryEntity);
        }
        if (taskRequestDto.getAssigneeName() != null) {
            UserTaskHistoryEntity userTaskHistoryEntity = new UserTaskHistoryEntity();
            userTaskHistoryEntity.setUserId(userService.findByUserNumber(taskRequestDto.getAssigneeName().split(" ")[2]));
            userTaskHistoryEntity.setTaskId(taskEntity);
            userTaskHistoryService.addUserTaskHistory(userTaskHistoryEntity);
        }
        logger.info("{} task has been created", taskRequestDto.getName());
        return taskMapper.taskEntityToDto(taskEntity);
    }

    @Override
    public TaskEntity addTaskEntity(TaskEntity taskEntity) {
        return taskRepository.save(taskEntity);
    }

    @Transactional
    @Override
    public TaskResponseDto editTask(TaskRequestDto taskRequestDto) {
        TaskEntity currentTaskState = getTaskEntityById(taskRequestDto.getId());
        if (TaskStatus.DONE.name().equals(taskRequestDto.getStatus()) && currentTaskState.getFinishDate() == null) {
            taskRequestDto.setFinishDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        } else {
            taskRequestDto.setFinishDate(null);
        }
        TaskEntity taskEntity = taskRepository.save(taskMapper.taskDtoToEntity(taskRequestDto));
        UserEntity currentUser = userTaskHistoryService.getCurrentUserByTask(taskEntity);
        if (taskRequestDto.getAssigneeName() != null && (currentUser == null ||
                !currentUser.getUserNumber().equals(Integer.valueOf(taskRequestDto.getAssigneeName().split(" ")[2])))) {
            UserTaskHistoryEntity userTaskHistoryEntity = new UserTaskHistoryEntity();
            userTaskHistoryEntity.setUserId(userService.findByUserNumber(taskRequestDto.getAssigneeName().split(" ")[2]));
            userTaskHistoryEntity.setTaskId(taskEntity);
            UserTaskHistoryEntity currentUTH = userTaskHistoryService.getCurrentByTask(taskEntity);
            if (currentUTH != null) {
                currentUTH.setValidToDate(OffsetDateTime.now());
                userTaskHistoryService.addUserTaskHistory(currentUTH);
            }
            userTaskHistoryService.addUserTaskHistory(userTaskHistoryEntity);
        }
        ReleaseEntity currentRelease = taskReleaseHistoryService.getCurrentReleaseByTask(taskEntity);
        if (taskRequestDto.getRelease() != null &&
                (currentRelease == null || !currentRelease.getId().toString().equals(taskRequestDto.getRelease()))) {
            TaskReleaseHistoryEntity taskReleaseHistoryEntity = new TaskReleaseHistoryEntity();
            taskReleaseHistoryEntity.setTaskId(taskEntity);
            taskReleaseHistoryEntity.setReleaseId(releaseService.getEntityById(UUID.fromString(taskRequestDto.getRelease())));
            if (taskReleaseHistoryService.findByTemplate(taskReleaseHistoryEntity) == null) {
                taskReleaseHistoryService.addTaskRelease(taskReleaseHistoryEntity);
            }
        }
        logger.info("{} has been edited", taskRequestDto.getName());
        return taskMapper.taskEntityToDto(taskEntity);
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskEntity> getTasksByProjectsName(String projectName) {
        return projectService.getProjectEntity(projectName).getTasks();
    }

    @Override
    public List<TaskEntity> getFilteredTasks(TaskRequestDto taskRequestDto, String projectName){
        ProjectEntity projectEntity = projectService.getProjectEntity(projectName);

        return taskRepository.findByNameAndEstCostsAndActualCostsAndProjectIdAndPriorityAndDescriptionAndComments(
                taskRequestDto.getName(), taskRequestDto.getEstCosts(), taskRequestDto.getActualCosts(), projectEntity,
                taskRequestDto.getPriority(), taskRequestDto.getDescription(), taskRequestDto.getComments());
    }

    @Override
    public List<TaskResponseDto> getAllFilteredProjectTasks(TaskRequestDto taskRequestDto, String projectName) {
        return getFilteredTasks(taskRequestDto, projectName).stream()
                .map(taskMapper::taskEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponseDto> getAllProjectTasks(String projectName) {
        return getTasksByProjectsName(projectName).stream()
                .map(taskMapper::taskEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskResponseDto> findTasksByStatus(String projectName, TaskStatus taskStatus) {
        return getTasksByProjectsName(projectName).stream().filter(task -> task.getTaskStatus() == taskStatus)
                .map(taskMapper::taskEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<String> getListOfTaskStatus() {
        return Arrays.stream(TaskStatus.values()).map(TaskStatus::toString).collect(Collectors.toList());
    }

    @Override
    public List<String> getListOfTaskTypes() {
        return Arrays.stream(TaskType.values()).map(TaskType::toString).collect(Collectors.toList());
    }

    // TODO: реализация фильтров задач
    @Transactional(readOnly = true)
    @Override
    public List<TaskResponseDto> getTasksByFilter(TaskRequestDto taskRequestDto, Principal principal) {



        return null;
    }
}
