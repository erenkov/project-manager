package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.controller.Dto.TaskResponseDto;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.TaskStatus;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.mappers.TaskMapper;
import com.developing.simbir_product.repository.TaskRepository;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskMapper taskMapper;


    @Transactional
    @Override
    public TaskResponseDto getById(UUID id) {

        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Task with ID = '%s' not found", id)));

        TaskResponseDto taskResponseDto = new TaskResponseDto();

        //todo TaskResponseDto = mapFrom taskEntity !!!!!!!!!!!!!!!!!!!!!!!!!!!

        return taskResponseDto;
    }


    @Transactional
    @Override
    public TaskResponseDto addTask(TaskRequestDto taskRequestDto) {

        TaskEntity taskEntity = taskRepository.save(taskMapper.taskDtoToEntity(taskRequestDto));

        return taskMapper.taskEntityToDto(taskEntity); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Override
    public TaskEntity addTaskEntity(TaskEntity taskEntity) {
        return taskRepository.save(taskEntity);
    }


    @Transactional
    @Override
    public TaskResponseDto editTask(TaskRequestDto taskRequestDto) {

        TaskEntity taskEntity = new TaskEntity();

        //todo taskEntity = mapFrom taskRequestDto ???????????????????????????????

        taskRepository.save(taskEntity);

        return new TaskResponseDto(); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
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
    public List<String> getListOfTaskTypes() {
        return Arrays.stream(TaskStatus.values()).map(TaskStatus::toString).collect(Collectors.toList());
    }

}
