package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.controller.Dto.TaskResponseDto;
import com.developing.simbir_product.entity.*;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.mappers.TaskMapper;
import com.developing.simbir_product.repository.TaskRepository;
import com.developing.simbir_product.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);
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
    public TaskResponseDto addTask(TaskRequestDto taskRequestDto) {
        TaskEntity taskEntity = taskRepository.save(taskMapper.taskDtoToEntity(taskRequestDto));
        UserTaskHistoryEntity userTaskHistoryEntity = new UserTaskHistoryEntity();
        userTaskHistoryEntity.setUserId(userService.findByUserNumber(taskRequestDto.getAssigneeName().split(" ")[2]));
        userTaskHistoryEntity.setTaskId(taskEntity);
        userTaskHistoryService.addUserTaskHistory(userTaskHistoryEntity);
        TaskReleaseHistoryEntity taskReleaseHistoryEntity = new TaskReleaseHistoryEntity();
        taskReleaseHistoryEntity.setTaskId(taskEntity);
        taskReleaseHistoryEntity.setReleaseId(releaseService.getEntityById(UUID.fromString(taskRequestDto.getRelease())));
        taskReleaseHistoryService.addTaskRelease(taskReleaseHistoryEntity);
        logger.trace("{} task has been created", taskRequestDto.getName());
        return taskMapper.taskEntityToDto(taskEntity);
    }

    @Override
    public TaskEntity addTaskEntity(TaskEntity taskEntity) {
        return taskRepository.save(taskEntity);
    }

    @Transactional
    @Override
    public TaskResponseDto editTask(TaskRequestDto taskRequestDto) {
        TaskEntity taskEntity = taskRepository.save(taskMapper.taskDtoToEntity(taskRequestDto));
        logger.trace("{} has been edited", taskRequestDto.getName());
        return taskMapper.taskEntityToDto(taskEntity);
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        taskRepository.deleteById(id);
    }

    public List<TaskEntity> getTasksByProjectsName(String projectName) {
        return projectService.getProjectEntity(projectName).getTasks();
    }

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
