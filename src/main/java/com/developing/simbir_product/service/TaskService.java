package com.developing.simbir_product.service;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.controller.Dto.TaskResponseDto;

import java.util.UUID;

public interface TaskService {

    TaskResponseDto getById(UUID id);

    TaskResponseDto addTask(TaskRequestDto taskRequestDto);

    TaskResponseDto editTask(TaskRequestDto taskRequestDto);

    void deleteById(UUID id);

    TaskResponseDto findByName(String name);

}
