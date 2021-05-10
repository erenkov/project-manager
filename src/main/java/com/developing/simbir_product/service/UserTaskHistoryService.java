package com.developing.simbir_product.service;

import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.entity.UserTaskHistoryEntity;

import java.util.List;
import java.util.UUID;


public interface UserTaskHistoryService {

//    UserTaskHistoryEntity getByTaskIdAndReleaseId(UUID userId, UUID taskId); //todo добавить 3 поле

    UserTaskHistoryEntity getById(UUID id);

    UserTaskHistoryEntity addUserTaskHistory(UserTaskHistoryEntity userTaskHistoryEntity);

    UserTaskHistoryEntity editUserTaskHistory(UserTaskHistoryEntity userTaskHistoryEntity);

    void deleteById(UUID id);

//    void deleteByUserIdAndTaskId(UUID userId, UUID taskId); //todo 3 поле

    UserEntity getCurrentUserByTask(TaskEntity taskEntity);

    List<TaskEntity> getTasksByUser(UserEntity userEntity);
}
