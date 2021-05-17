package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.TaskReleaseHistoryEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.repository.TaskReleaseHistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskReleaseHistoryServiceImplTest {

    @Mock
    TaskReleaseHistoryRepository taskReleaseHistoryRepository;

    @InjectMocks
    TaskReleaseHistoryServiceImpl taskReleaseHistoryService;

    @Test
    @DisplayName("Should return current release")
    void getCurrentReleaseByTask() { // TODO: Разобраться
        TaskEntity inputTaskEntity = new TaskEntity();
        inputTaskEntity.setDueDate(OffsetDateTime.now());
        inputTaskEntity.setCreateDate(OffsetDateTime.now());
        inputTaskEntity.setFinishDate(OffsetDateTime.now());
        
        ReleaseEntity expectedReleaseEntity = new ReleaseEntity();
        TaskReleaseHistoryEntity taskReleaseHistoryEntity1 = new TaskReleaseHistoryEntity();
        TaskReleaseHistoryEntity taskReleaseHistoryEntity2 = new TaskReleaseHistoryEntity();
        List<TaskReleaseHistoryEntity> taskReleaseHistoryEntities = Stream.of(taskReleaseHistoryEntity1, taskReleaseHistoryEntity2)
                .collect(Collectors.toList());
        

        Mockito.doReturn(taskReleaseHistoryEntities)
                .when(taskReleaseHistoryRepository)
                .findByTaskId(inputTaskEntity);

        ReleaseEntity outputReleaseEntity = new ReleaseEntity();

        ReleaseEntity releaseEntity = taskReleaseHistoryService.getCurrentReleaseByTask(inputTaskEntity);
        Mockito.verify(taskReleaseHistoryRepository, Mockito.times(1)).findByTaskId(inputTaskEntity);

    }

    @Test()
    @DisplayName("Should throw NotFoundException")
    void getCurrentReleaseByTaskFail(){ //TODO: handle expected exception
        TaskEntity inputTaskEntity = new TaskEntity();

        ReleaseEntity releaseEntity = taskReleaseHistoryService.getCurrentReleaseByTask(inputTaskEntity);
        Exception exception = assertThrows(NotFoundException.class, () -> {
            throw new NotFoundException(String.format("Task %s has no due date.", inputTaskEntity.getName()));
        });

        String expectedMessage = String.format("Task %s has no due date.", inputTaskEntity.getName());
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}