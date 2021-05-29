package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.controller.Dto.TaskResponseDto;
import com.developing.simbir_product.entity.*;
import com.developing.simbir_product.mappers.TaskMapper;
import com.developing.simbir_product.repository.TaskRepository;
import com.developing.simbir_product.service.ReleaseService;
import com.developing.simbir_product.service.TaskReleaseHistoryService;
import com.developing.simbir_product.service.UserService;
import com.developing.simbir_product.service.UserTaskHistoryService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    TaskMapper taskMapper;

    @Mock
    TaskRepository taskRepository;

    @Mock
    UserService userService;

    @Mock
    ReleaseService releaseService;

    @Mock
    UserTaskHistoryService userTaskHistoryService;

    @Mock
    TaskReleaseHistoryService taskReleaseHistoryService;

    @InjectMocks
    TaskServiceImpl taskService;

    @Captor
    private ArgumentCaptor<TaskEntity> captor;


    @Test
    @DisplayName("Should find by ID")
    void should_find_by_id() { // TODO: разобраться, в чем ошибка
        UUID uuid = UUID.randomUUID();
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(uuid);
        taskEntity.setName("test");

        TaskResponseDto expectedTaskResponseDto = new TaskResponseDto();
        expectedTaskResponseDto.setName("test");

        Mockito.doReturn(Optional.of(TaskEntity.class))
                .when(taskRepository)
                .findById(uuid);

        Mockito.doReturn(expectedTaskResponseDto)
                .when(taskMapper)
                .taskEntityToDto(ArgumentMatchers.any(TaskEntity.class));

        TaskResponseDto actualTaskResponseDto = taskService.getById(uuid);

        Assertions.assertThat(actualTaskResponseDto.getId()).isEqualTo(expectedTaskResponseDto.getId());
        Assertions.assertThat(actualTaskResponseDto.getName()).isEqualTo(expectedTaskResponseDto.getName());

    }

    @Test
    @DisplayName(("Should add task"))
    void should_add_task() {
        UUID uuid = UUID.randomUUID();
        UUID releaseUUID = UUID.randomUUID();
        UUID userUUID = UUID.randomUUID();
        TaskRequestDto expectedDto = new TaskRequestDto();
        expectedDto.setId(uuid.toString());
        expectedDto.setName("test");
        expectedDto.setAssigneeName("testName testSurname 1");
        expectedDto.setRelease(releaseUUID.toString());

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(uuid);
        taskEntity.setName("testTask");

        ReleaseEntity releaseEntity = new ReleaseEntity();
        releaseEntity.setId(releaseUUID);
        releaseEntity.setName("testRelease");

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userUUID);
        userEntity.setLogin("testUser");

        TaskResponseDto output = new TaskResponseDto();
        output.setName("test");

        UserTaskHistoryEntity userTaskHistoryEntity = new UserTaskHistoryEntity();
        userTaskHistoryEntity.setUserId(userEntity);
        userTaskHistoryEntity.setTaskId(taskEntity);

        TaskReleaseHistoryEntity taskReleaseHistoryEntity = new TaskReleaseHistoryEntity();
        taskReleaseHistoryEntity.setTaskId(taskEntity);
        taskReleaseHistoryEntity.setReleaseId(releaseEntity);

        Mockito.doReturn(taskEntity)
                .when(taskMapper)
                .taskDtoToEntity(expectedDto);

        Mockito.doReturn(output)
                .when(taskMapper)
                .taskEntityToDto(taskEntity);

        Mockito.doReturn(new UserEntity())
                .when(userService)
                .findByUserNumber(ArgumentMatchers.any(String.class));

        Mockito.doReturn(taskEntity)
                .when(taskRepository)
                .save(ArgumentMatchers.any(TaskEntity.class));

        Mockito.doReturn(releaseEntity)
                .when(releaseService)
                .getEntityById(UUID.fromString(expectedDto.getRelease()));

        TaskResponseDto actualDto = taskService.addTask(expectedDto);
        Assertions.assertThat(expectedDto.getName()).isEqualTo(actualDto.getName());

        Mockito.verify(taskMapper, Mockito.times(1))
                .taskEntityToDto(taskEntity);
        Mockito.verify(taskMapper, Mockito.times(1))
                .taskDtoToEntity(expectedDto);
        Mockito.verify(taskRepository, Mockito.times(1))
                .save(captor.capture());
        Assertions.assertThat(captor.getValue().getName()).isEqualTo("testTask");
        Mockito.verify(userTaskHistoryService, Mockito.times(1))
                .addUserTaskHistory(ArgumentMatchers.any(UserTaskHistoryEntity.class));
        Mockito.verify(taskReleaseHistoryService, Mockito.times(1))
                .addTaskRelease(ArgumentMatchers.any(TaskReleaseHistoryEntity.class));
    }
}