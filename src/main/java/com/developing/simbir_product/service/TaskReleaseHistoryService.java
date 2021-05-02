package com.developing.simbir_product.service;


import com.developing.simbir_product.entity.TaskReleaseHistoryEntity;

import java.util.UUID;

public interface TaskReleaseHistoryService {

    TaskReleaseHistoryEntity getById(UUID id);

    TaskReleaseHistoryEntity addTaskRelease(TaskReleaseHistoryEntity taskReleaseHistoryEntity);

    TaskReleaseHistoryEntity editTaskRelease(TaskReleaseHistoryEntity taskReleaseHistoryEntity);

    void deleteById(UUID id);

}
