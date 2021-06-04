package com.developing.simbir_product.controllers;

import com.developing.simbir_product.controller.TaskReleaseHistoryRestController;
import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.service.ReleaseService;
import com.developing.simbir_product.service.TaskReleaseHistoryService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class TaskReleaseHistoryRestControllerTest {

    @Mock
    TaskReleaseHistoryService taskReleaseHistoryService;

    @Mock
    ReleaseService releaseService;

    @InjectMocks
    TaskReleaseHistoryRestController taskReleaseHistoryRestController;

    @Test
    @DisplayName("Should return unfinished tasks by given release name")
    void shouldReturnUnfinishedTasks(){
        TaskEntity taskEntity1 = new TaskEntity();
        taskEntity1.setName("Test name");
        List<TaskEntity> expectedList = Stream.of(taskEntity1).collect(Collectors.toList());
        ReleaseEntity releaseEntity = new ReleaseEntity();

        Mockito.doReturn(releaseEntity)
                .when(releaseService)
                .getByName("Test");

        Mockito.doReturn(expectedList)
                .when(taskReleaseHistoryService)
                .findUnfinishedTasksByReleaseId(ArgumentMatchers.any(ReleaseEntity.class));

        List<TaskEntity> actualList = taskReleaseHistoryRestController.getUnfinishedTasksByRelease("Test");

        Mockito.verify(releaseService, Mockito.times(1)).getByName("Test");
        Mockito.verify(taskReleaseHistoryService, Mockito.times(1)).findUnfinishedTasksByReleaseId(releaseEntity);

        Assertions.assertThat(actualList.get(0).getName()).isEqualTo(expectedList.get(0).getName());

    }
}
