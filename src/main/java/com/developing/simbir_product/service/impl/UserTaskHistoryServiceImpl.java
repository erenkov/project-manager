package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.entity.UserTaskHistoryEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.repository.UserTaskHistoryRepository;
import com.developing.simbir_product.service.UserTaskHistoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class UserTaskHistoryServiceImpl implements UserTaskHistoryService {

    @Autowired
    private UserTaskHistoryRepository userTaskHistoryRepository;

    @Override
    public UserTaskHistoryEntity getByTaskIdAndReleaseId(UUID userId, UUID taskId) { //todo 3 поле добавить из ключа
        return userTaskHistoryRepository.findByUserIdAndTaskId(userId, taskId).orElseThrow(
                () -> new NotFoundException("UserTaskHistory with ID = ' ' not found")
        );  //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Override
    public UserTaskHistoryEntity addUserTaskHistory(UserTaskHistoryEntity userTaskHistoryEntity) {
        return userTaskHistoryRepository.save(userTaskHistoryEntity);  //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Override
    public UserTaskHistoryEntity editUserTaskHistory(UserTaskHistoryEntity userTaskHistoryEntity) {
        return userTaskHistoryRepository.save(userTaskHistoryEntity);  //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Override
    public void deleteByUserIdAndTaskId(UUID userId, UUID taskId) { //todo 3 поле добавить из ключа
        userTaskHistoryRepository.deleteByUserIdAndTaskId(userId, taskId);  //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

}
