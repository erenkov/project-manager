package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.controller.Dto.TaskResponseDto;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.mappers.TaskMapper;
import com.developing.simbir_product.repository.TaskRepository;
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
        TaskRequestDto expectedDto = new TaskRequestDto();
        expectedDto.setName("test");

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setName("test");

        TaskResponseDto output = new TaskResponseDto();
        output.setName("test");

        Mockito.doReturn(taskEntity)
                .when(taskMapper)
                .taskDtoToEntity(expectedDto);

        Mockito.doReturn(output)
                .when(taskMapper)
                .taskEntityToDto(taskEntity);

        TaskResponseDto actualDto = taskService.addTask(expectedDto);
        Assertions.assertThat(expectedDto.getName()).isEqualTo(actualDto.getName());

        Mockito.verify(taskMapper, Mockito.times(1))
                .taskEntityToDto(ArgumentMatchers.any(TaskEntity.class));
        Mockito.verify(taskMapper, Mockito.times(1))
                .taskDtoToEntity(ArgumentMatchers.any(TaskRequestDto.class));
        Mockito.verify(taskRepository, Mockito.times(1))
                .save(captor.capture());
        Assertions.assertThat(captor.getValue().getName()).isEqualTo("test");
    }
}