package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.controller.Dto.TaskResponseDto;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.TaskStatus;
import com.developing.simbir_product.entity.TaskType;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.mappers.TaskMapper;
import com.developing.simbir_product.repository.TaskRepository;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.TaskService;
import com.developing.simbir_product.service.UserService;
import com.developing.simbir_product.service.UserTaskHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.security.Principal;
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
    private UserTaskHistoryService userTaskHistoryService;

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
    public void addTask(TaskRequestDto taskRequestDto) {

        TaskEntity taskEntity = taskRepository.save(taskMapper.taskDtoToEntity(taskRequestDto));

        logger.trace("{} task has been created", taskRequestDto.getName());
//        return taskMapper.taskEntityToDto(taskEntity); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
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
        return taskMapper.taskEntityToDto(taskEntity); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }


    @Transactional
    @Override
    public void deleteById(UUID id) {
        taskRepository.deleteById(id); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }


    @Transactional
    @Override
    public TaskResponseDto findByName(String name) {

        TaskEntity taskEntity = taskRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Task with name = '%s' not found", name)));

        TaskResponseDto taskResponseDto = new TaskResponseDto();

        //todo TaskResponseDto = mapFrom taskEntity !!!!!!!!!!!!!!!!!!!!!!!!

        return taskResponseDto;
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

    @Transactional(readOnly = true)
    @Override
    public List<TaskResponseDto> getTasksByFilter(TaskRequestDto taskRequestDto, Principal principal) {
        TaskEntity example = taskMapper.taskDtoToEntity(taskRequestDto);
        UserEntity userEntity = userService.findUserEntity(principal.getName());
        List<TaskEntity> usersTasks = userTaskHistoryService.getTasksByUser(userEntity);
        return usersTasks.stream()
                .filter(task -> example.getName() == null || task.getName().equals(example.getName()))
                .filter(task -> example.getTaskType() == null || task.getTaskType().equals(example.getTaskType()))
                .filter(task -> example.getTaskStatus() == null || task.getTaskStatus().equals(example.getTaskStatus()))
                .filter(task -> example.getDueDate() == null || task.getDueDate().equals(example.getDueDate()))
                .filter(task -> example.getFinishDate() == null || task.getFinishDate().equals(example.getFinishDate()))
                .filter(task -> example.getComments() == null || task.getComments().equals(example.getComments()))
                .filter(task -> example.getCreateDate() == null || task.getCreateDate().equals(example.getCreateDate()))
                .filter(task -> example.getProjectId() == null || task.getProjectId().equals(example.getProjectId()))
                .filter(task -> example.getActualCosts() == 0 || task.getActualCosts() == example.getActualCosts())
                .filter(task -> example.getEstCosts() == 0 || task.getEstCosts() == example.getEstCosts())
                .filter(task -> example.getPriority() == 0 || task.getPriority() == example.getPriority())
                .map(taskMapper::taskEntityToDto)
                .collect(Collectors.toList());
    }

    public TaskEntity getTaskById(UUID id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Task with id = '%s' not found", id))
        );
    }
}
