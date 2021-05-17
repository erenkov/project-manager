package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.entity.UserTaskHistoryEntity;
import com.developing.simbir_product.repository.UserTaskHistoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserTaskHistoryServiceImplTest {

    @Mock
    private UserTaskHistoryRepository userTaskHistoryRepository;

    @InjectMocks
    UserTaskHistoryServiceImpl userTaskHistoryService;

    @Test
    @DisplayName("Should return user by task")
    void getCurrentUserByTask() {
        UUID uuid = UUID.randomUUID();
        TaskEntity inputTaskEntity = new TaskEntity();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(uuid);
        UserTaskHistoryEntity userTaskHistoryEntity = new UserTaskHistoryEntity();
        userTaskHistoryEntity.setUserId(userEntity);


        Mockito.doReturn(Optional.of(userTaskHistoryEntity))
                .when(userTaskHistoryRepository)
                .findByTaskIdAndValidToDateIsAfter(
                        ArgumentMatchers.any(TaskEntity.class), ArgumentMatchers.any(OffsetDateTime.class));

        UserEntity outputUserEntity = userTaskHistoryService.getCurrentUserByTask(inputTaskEntity);
        Mockito.verify(userTaskHistoryRepository, Mockito.times(1))
                .findByTaskIdAndValidToDateIsAfter(
                        ArgumentMatchers.any(TaskEntity.class), ArgumentMatchers.any(OffsetDateTime.class));
        Assertions.assertThat(outputUserEntity.getId()).isEqualTo(userEntity.getId());
    }

    @Test
    void getTasksByUser() {
        UserEntity userEntity = new UserEntity();
        UserTaskHistoryEntity userTaskHistoryEntity = new UserTaskHistoryEntity();
        List<UserTaskHistoryEntity> taskEntities = Stream.of(userTaskHistoryEntity).collect(Collectors.toList());

        Mockito.doReturn(taskEntities)
                .when(userTaskHistoryRepository)
                .findAllByUserId(userEntity);

        List<TaskEntity> outputTaskEntities = userTaskHistoryService.getTasksByUser(userEntity);
        Mockito.verify(userTaskHistoryRepository, Mockito.times(1)).findAllByUserId(userEntity);

    }
}