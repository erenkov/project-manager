package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.entity.UserTaskHistoryEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.repository.UserTaskHistoryRepository;
import com.developing.simbir_product.service.UserTaskHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class UserTaskHistoryServiceImpl implements UserTaskHistoryService {

    @Autowired
    private UserTaskHistoryRepository userTaskHistoryRepository;

//    @Transactional
//    @Override
//    public UserTaskHistoryEntity getByTaskIdAndReleaseId(UUID userId, UUID taskId) {
//        return userTaskHistoryRepository.findByUserIdAndTaskId(userId, taskId).orElseThrow(
//                () -> new NotFoundException("UserTaskHistory with ID = ' ' not found")
//        );  //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
//    }

    @Transactional
    @Override
    public UserTaskHistoryEntity getById(UUID id) {
        return userTaskHistoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("UserTaskHistory with ID = ' ' not found")
        );  //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Transactional
    @Override
    public UserTaskHistoryEntity addUserTaskHistory(UserTaskHistoryEntity userTaskHistoryEntity) {
        return userTaskHistoryRepository.save(userTaskHistoryEntity);  //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Transactional
    @Override
    public UserTaskHistoryEntity editUserTaskHistory(UserTaskHistoryEntity userTaskHistoryEntity) {
        return userTaskHistoryRepository.save(userTaskHistoryEntity);  //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        userTaskHistoryRepository.deleteById(id);  //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

//    @Transactional
//    @Override
//    public void deleteByUserIdAndTaskId(UUID userId, UUID taskId) {
//        userTaskHistoryRepository.deleteByUserIdAndTaskId(userId, taskId);  //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
//    }

}
