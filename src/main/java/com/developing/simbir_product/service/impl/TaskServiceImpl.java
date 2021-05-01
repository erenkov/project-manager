package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.TaskStatus;
import com.developing.simbir_product.entity.TaskType;
import com.developing.simbir_product.repository.TaskRepository;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Random;
import java.util.UUID;


@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectService projectService;



    @Override
    public void addTask() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setProjectId(projectService.findByName("Проект-1"));
        taskEntity.setDescription("Description");
        taskEntity.setName("Task Name " + new Random().nextInt());
        taskEntity.setTaskType(TaskType.FEATURE);
        taskEntity.setTaskStatus(TaskStatus.BACKLOG);
        taskEntity.setActualCosts(100);
        taskEntity.setCreateDate(OffsetDateTime.now());
        taskEntity.setDueDate(OffsetDateTime.now());
        taskEntity.setFinishDate(OffsetDateTime.now());
        taskEntity.setEstCosts(100);
        taskEntity.setComments("Comments");
        taskEntity.setPriority(999);

        taskRepository.save(taskEntity);

    }

    @Override
    public TaskEntity getById(UUID id) {
        return taskRepository.getOne(id);
    }

}
