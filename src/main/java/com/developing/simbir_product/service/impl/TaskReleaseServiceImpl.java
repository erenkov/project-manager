package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.entity.TaskReleaseHistoryEntity;
import com.developing.simbir_product.repository.TaskReleaseRepository;
import com.developing.simbir_product.service.ReleaseService;
import com.developing.simbir_product.service.TaskReleaseService;
import com.developing.simbir_product.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class TaskReleaseServiceImpl implements TaskReleaseService {

    @Autowired
    private TaskReleaseRepository taskReleaseRepository;

    @Autowired
    private ReleaseService releaseService;

    @Autowired
    private TaskService taskService;



    @Override
    public void addTaskRelease() {
//        TaskReleaseHistoryEntity taskReleaseEntity = new TaskReleaseHistoryEntity();
//        TaskReleaseId taskReleaseId = new TaskReleaseId(UUID.fromString("3a3abeb4-d2ee-4d44-a56c-9442580cbc2d"),
//                UUID.fromString("eaf9f54c-8bdc-4033-b1fc-49ae70412032"));
//
//        taskReleaseEntity.setTaskReleaseId(taskReleaseId);
//        taskReleaseEntity.setCompleted(false);
//        taskReleaseRepository.save(taskReleaseEntity);

    }
}
