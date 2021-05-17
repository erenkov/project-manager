package com.developing.simbir_product.service;


import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.TaskReleaseHistoryEntity;

import java.util.UUID;

public interface TaskReleaseHistoryService {

    TaskReleaseHistoryEntity addTaskRelease(TaskReleaseHistoryEntity taskReleaseHistoryEntity);

    void deleteById(UUID id);

    ReleaseEntity getCurrentReleaseByTask(TaskEntity taskEntity);
}
