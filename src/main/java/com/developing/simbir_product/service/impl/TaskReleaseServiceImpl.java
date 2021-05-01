package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.entity.TaskReleaseEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.repository.TaskReleaseRepository;
import com.developing.simbir_product.service.TaskReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TaskReleaseServiceImpl implements TaskReleaseService {

    @Autowired
    private TaskReleaseRepository taskReleaseRepository;

    @Override
    public TaskReleaseEntity getByTaskIdAndReleaseId(UUID taskId, UUID releaseId) {
        return taskReleaseRepository.findByTaskIdAndReleaseId(taskId, releaseId).orElseThrow(
                () -> new NotFoundException("TaskRelease with ID = ' ' not found")
        ); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Override
    public TaskReleaseEntity addTaskRelease(TaskReleaseEntity taskReleaseEntity) {
        return taskReleaseRepository.save(taskReleaseEntity); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Override
    public TaskReleaseEntity editTaskRelease(TaskReleaseEntity taskReleaseEntity) {
        return taskReleaseRepository.save(taskReleaseEntity); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Override
    public void deleteByTaskIdAndReleaseId(UUID taskId, UUID releaseId) {
        taskReleaseRepository.deleteByTaskIdAndReleaseId(taskId, releaseId); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

}
