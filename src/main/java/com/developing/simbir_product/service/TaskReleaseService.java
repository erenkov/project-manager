package com.developing.simbir_product.service;


import com.developing.simbir_product.entity.TaskReleaseEntity;

import java.util.UUID;

public interface TaskReleaseService {

    TaskReleaseEntity getByTaskIdAndReleaseId(UUID taskId, UUID releaseId);

    TaskReleaseEntity addTaskRelease(TaskReleaseEntity taskReleaseEntity);

    TaskReleaseEntity editTaskRelease(TaskReleaseEntity taskReleaseEntity);

    void deleteByTaskIdAndReleaseId(UUID taskId, UUID releaseId);

}
