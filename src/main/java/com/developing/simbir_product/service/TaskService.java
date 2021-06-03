package com.developing.simbir_product.service;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.controller.Dto.TaskResponseDto;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.TaskStatus;

import java.security.Principal;
import java.util.List;
import java.util.UUID;


public interface TaskService {

    TaskResponseDto getById(UUID id);

    TaskResponseDto getByStringId(String id);

    TaskEntity getTaskEntityById(String id);

    TaskResponseDto addTask(TaskRequestDto taskRequestDto);

    TaskEntity addTaskEntity(TaskEntity taskEntity);

    TaskResponseDto editTask(TaskRequestDto taskRequestDto);

    void deleteById(UUID id);

    List<TaskResponseDto> getAllProjectTasks(String projectName);

    List<TaskEntity> getTasksByProjectsName(String projectName);

    List<TaskResponseDto> findTasksByStatus(String projectName, TaskStatus taskStatus);

    List<String> getListOfTaskStatus();

    List<String> getListOfTaskTypes();

    List<TaskResponseDto> getTasksByFilter(TaskRequestDto taskRequestDto, Principal http);
}
