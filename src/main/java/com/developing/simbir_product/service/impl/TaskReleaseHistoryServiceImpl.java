package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.TaskReleaseHistoryEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.repository.TaskReleaseHistoryRepository;
import com.developing.simbir_product.service.TaskReleaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TaskReleaseHistoryServiceImpl implements TaskReleaseHistoryService {

    @Autowired
    private TaskReleaseHistoryRepository taskReleaseHistoryRepository;

    @Transactional
    @Override
    public TaskReleaseHistoryEntity addTaskRelease(TaskReleaseHistoryEntity taskReleaseHistoryEntity) {
        return taskReleaseHistoryRepository.save(taskReleaseHistoryEntity);
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        taskReleaseHistoryRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ReleaseEntity getCurrentReleaseByTask(TaskEntity taskEntity) {
        if (taskEntity.getDueDate() == null) {
            throw new NotFoundException(String.format("Task %s has no due date.", taskEntity.getName()));
        }
        return taskReleaseHistoryRepository.findByTaskId(taskEntity).stream()
                .map(TaskReleaseHistoryEntity::getReleaseId)
                .filter(releaseEntity -> taskEntity.getDueDate().isAfter(releaseEntity.getStartDate()) &&
                        taskEntity.getDueDate().isBefore(releaseEntity.getFinishDate()))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
