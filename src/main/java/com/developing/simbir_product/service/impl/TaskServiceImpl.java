package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.controller.Dto.TaskResponseDto;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.repository.TaskRepository;
import com.developing.simbir_product.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    private TaskRepository taskRepository;


    @Transactional
    @Override
    public TaskResponseDto getById(UUID id) {

        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Task with ID = ' ' not found")
        );

        TaskResponseDto taskResponseDto = new TaskResponseDto();

        //todo TaskResponseDto = mapFrom taskEntity !!!!!!!!!!!!!!!!!!!!!!!!!!!

        return taskResponseDto;
    }


    @Transactional
    @Override
    public TaskResponseDto addTask(TaskRequestDto taskRequestDto) {

        TaskEntity taskEntity = new TaskEntity();

        //todo taskEntity = mapFrom taskRequestDto ??????????????????????????

        taskRepository.save(taskEntity);

        return new TaskResponseDto(); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
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


    @Override
    public TaskResponseDto findByName(String name) {

        TaskEntity taskEntity = taskRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("Task with name = ' ' not found")
        );

        TaskResponseDto taskResponseDto = new TaskResponseDto();

        //todo TaskResponseDto = mapFrom taskEntity !!!!!!!!!!!!!!!!!!!!!!!!

        return taskResponseDto;
    }
}
