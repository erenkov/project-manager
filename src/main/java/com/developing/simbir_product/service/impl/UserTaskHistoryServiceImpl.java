package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.entity.UserTaskHistoryEntity;
import com.developing.simbir_product.repository.UserTaskHistoryRepository;
import com.developing.simbir_product.service.UserTaskHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
        return userTaskHistoryRepository.save(userTaskHistoryEntity);
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
                findByTaskIdAndValidToDateIsAfter(taskEntity, OffsetDateTime.now()).orElse(null);
        return (current != null) ? current.getUserId() : null;
    }

    @Override
    public UserTaskHistoryEntity getCurrentByTask(TaskEntity taskEntity) {
        return userTaskHistoryRepository.
                findByTaskIdAndValidToDateIsAfter(taskEntity, OffsetDateTime.now()).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TaskEntity> getTasksByUser(UserEntity userEntity) {
        return userTaskHistoryRepository.findAllByUserId(userEntity).stream()
                .map(UserTaskHistoryEntity::getTaskId)
                .collect(Collectors.toList());
    }

    @Override
    public UserTaskHistoryEntity findByTemplate(UserTaskHistoryEntity userTaskHistoryEntity) {
        List<UserTaskHistoryEntity> entities = userTaskHistoryRepository.findAll(Example.of(userTaskHistoryEntity));
        return entities.isEmpty() ? null : entities.get(0);
    }

    @Override
    public void deleteAllByTask(TaskEntity taskEntity) {
        if (taskEntity == null) {
            throw new IllegalArgumentException("Can't delete empty task");
        }
        userTaskHistoryRepository.deleteAllByTaskId(taskEntity);
    }
}
