package com.developing.simbir_product.service;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.controller.Dto.TaskResponseDto;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.TaskStatus;

import java.util.List;
import java.util.UUID;


public interface TaskService {

    TaskResponseDto getById(UUID id);

    TaskResponseDto addTask(TaskRequestDto taskRequestDto);

    TaskEntity addTaskEntity(TaskEntity taskEntity);

    TaskResponseDto editTask(TaskRequestDto taskRequestDto);

    void deleteById(UUID id);

    TaskResponseDto findByName(String name);

    List<TaskEntity> getTasksByProjectsName(String projectName);

    List<TaskResponseDto> findTasksByStatus(String projectName, TaskStatus taskStatus);

    List<String> getListOfTaskTypes();
}
