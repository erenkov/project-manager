package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.repository.TaskRepository;
import com.developing.simbir_product.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;


}
