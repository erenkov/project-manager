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
    public TaskReleaseHistoryEntity getById(UUID id) {
        return taskReleaseHistoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("TaskRelease with ID = '%s' not found", id))
        ); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Transactional
    @Override
    public TaskReleaseHistoryEntity addTaskRelease(TaskReleaseHistoryEntity taskReleaseHistoryEntity) {
        return taskReleaseHistoryRepository.save(taskReleaseHistoryEntity); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Transactional
    @Override
    public TaskReleaseHistoryEntity editTaskRelease(TaskReleaseHistoryEntity taskReleaseHistoryEntity) {
        return taskReleaseHistoryRepository.save(taskReleaseHistoryEntity); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        taskReleaseHistoryRepository.deleteById(id); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Transactional
    @Override
    public ReleaseEntity getCurrentReleaseByTask(TaskEntity taskEntity) {
        return taskReleaseHistoryRepository.findByTaskId(taskEntity).stream()
                .map(TaskReleaseHistoryEntity::getReleaseId)
                .filter(releaseEntity -> taskEntity.getDueDate().isAfter(releaseEntity.getStartDate()) &&
                        taskEntity.getDueDate().isBefore(releaseEntity.getFinishDate()))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}
