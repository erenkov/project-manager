package com.developing.simbir_product.service;

import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.entity.UserTaskHistoryEntity;

import java.util.List;
import java.util.UUID;


public interface UserTaskHistoryService {

    UserTaskHistoryEntity addUserTaskHistory(UserTaskHistoryEntity userTaskHistoryEntity);

    void deleteById(UUID id);

    UserEntity getCurrentUserByTask(TaskEntity taskEntity);

    UserTaskHistoryEntity getCurrentByTask(TaskEntity taskEntity);

    List<TaskEntity> getTasksByUser(UserEntity userEntity);

    UserTaskHistoryEntity findByTemplate(UserTaskHistoryEntity userTaskHistoryEntity);
}
