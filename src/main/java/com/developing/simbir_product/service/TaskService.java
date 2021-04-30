package com.developing.simbir_product.service;

import com.developing.simbir_product.entity.TaskEntity;

import java.util.UUID;


public interface TaskService {

    void addTask();

    TaskEntity getById(UUID id);
}
