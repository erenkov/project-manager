package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.ReleaseResponseDto;
import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.TaskReleaseHistoryEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.mappers.ReleaseMapper;
import com.developing.simbir_product.repository.TaskReleaseHistoryRepository;
import com.developing.simbir_product.service.TaskReleaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
public class TaskReleaseHistoryServiceImpl implements TaskReleaseHistoryService {

    @Autowired
    private TaskReleaseHistoryRepository taskReleaseHistoryRepository;
    @Autowired
    private ReleaseMapper releaseMapper;

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
                .orElse(null);
    }

    @Transactional
    @Override
    public ReleaseResponseDto getCurrentReleaseDtoByTask(TaskEntity taskEntity) {
        ReleaseEntity currentRelease = getCurrentReleaseByTask(taskEntity);
        return currentRelease == null ? null : releaseMapper.releaseEntityToDto(currentRelease);
    }

    @Transactional
    @Override
    public TaskReleaseHistoryEntity findByTemplate(TaskReleaseHistoryEntity taskReleaseHistoryEntity) {
        List<TaskReleaseHistoryEntity> entities = taskReleaseHistoryRepository.findAll(Example.of(taskReleaseHistoryEntity));
        return entities.isEmpty() ? null : entities.get(0);
    }
}
