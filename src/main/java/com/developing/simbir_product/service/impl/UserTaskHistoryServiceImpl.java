package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.entity.UserTaskHistoryEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.repository.UserTaskHistoryRepository;
import com.developing.simbir_product.service.UserTaskHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserTaskHistoryServiceImpl implements UserTaskHistoryService {

    @Autowired
    private UserTaskHistoryRepository userTaskHistoryRepository;

    @Transactional
    @Override
    public UserTaskHistoryEntity addUserTaskHistory(UserTaskHistoryEntity userTaskHistoryEntity) {
        return userTaskHistoryRepository.save(userTaskHistoryEntity);  //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        userTaskHistoryRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UserEntity getCurrentUserByTask(TaskEntity taskEntity) {
        UserTaskHistoryEntity current = userTaskHistoryRepository.
                findByTaskIdAndValidToDateIsAfter(taskEntity, OffsetDateTime.now())
                .orElseThrow(() -> new NotFoundException(String.format("Task %s has no assignee.",
                        taskEntity.getName())));
        return current.getUserId();
    }

    @Transactional(readOnly = true)
    @Override
    public List<TaskEntity> getTasksByUser(UserEntity userEntity) {
        return userTaskHistoryRepository.findAllByUserId(userEntity).stream()
                .map(UserTaskHistoryEntity::getTaskId)
                .collect(Collectors.toList());
    }
}
