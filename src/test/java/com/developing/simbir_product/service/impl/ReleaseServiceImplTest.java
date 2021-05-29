package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import com.developing.simbir_product.controller.Dto.ReleaseResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.mappers.DateTimeMapper;
import com.developing.simbir_product.mappers.ReleaseMapper;
import com.developing.simbir_product.repository.ReleaseRepository;
import com.developing.simbir_product.service.ProjectService;
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

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ReleaseServiceImplTest {

    @Mock
    ReleaseRepository releaseRepository;

    @Mock
    ReleaseMapper releaseMapper;

    @Mock
    ProjectService projectService;

    @Mock
    TaskReleaseHistoryService taskReleaseHistoryService;

    @Mock
    DateTimeMapper dateTimeMapper;

    @InjectMocks
    ReleaseServiceImpl releaseService;

    @Test
    @DisplayName("Should find by ID")
    void getById() {
        UUID uuid = UUID.randomUUID();
        ReleaseResponseDto releaseResponseDto = new ReleaseResponseDto();
        releaseResponseDto.setId(uuid.toString());

        ReleaseEntity releaseEntity = new ReleaseEntity();
        releaseEntity.setId(uuid);

        Mockito.doReturn(Optional.of(releaseEntity))
                .when(releaseRepository)
                .findById(uuid);

        Mockito.doReturn(releaseResponseDto)
                .when(releaseMapper)
                .releaseEntityToDto(releaseEntity);

        ReleaseResponseDto output = releaseService.getById(uuid);
        Mockito.verify(releaseRepository, Mockito.times(1)).findById(uuid);
        Mockito.verify(releaseMapper, Mockito.times(1)).releaseEntityToDto(releaseEntity);
        Assertions.assertThat(output.getId()).isEqualTo(uuid.toString());
    }

    @Test
    @DisplayName("Should add release")
    void addRelease() { //TODO: Разобраться
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime finishDate = LocalDateTime.now();
        ReleaseRequestDto releaseRequestDto = new ReleaseRequestDto();
        releaseRequestDto.setName("testName");
        releaseRequestDto.setProjectName("testProjectName");
        releaseRequestDto.setStartDate(startDate);
        releaseRequestDto.setFinishDate(finishDate);
        ReleaseEntity releaseEntity = new ReleaseEntity();
        releaseEntity.setName("testName");
        releaseEntity.setStartDate(startDate.atOffset(ZoneOffset.UTC));
        releaseEntity.setFinishDate(finishDate.atOffset(ZoneOffset.UTC));
        ReleaseEntity releaseEntity1 = new ReleaseEntity();
        releaseEntity.setName("testName1");
        releaseEntity.setStartDate(startDate.atOffset(ZoneOffset.UTC));
        releaseEntity.setFinishDate(finishDate.atOffset(ZoneOffset.UTC));
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName("testName");
        projectEntity.setStartDate(startDate.atOffset(ZoneOffset.UTC));
        projectEntity.setFinishDate(finishDate.atOffset(ZoneOffset.UTC));
        List<ReleaseEntity> releaseEntities = List.of(releaseEntity, releaseEntity1);

        Mockito.doReturn(releaseEntity)
                .when(releaseMapper)
                .releaseDtoToEntity(releaseRequestDto);

        Mockito.doReturn(projectEntity)
                .when(projectService)
                .getProjectEntity(releaseRequestDto.getProjectName());

        Mockito.doReturn(Optional.of(releaseEntities))
                .when(releaseRepository)
                .findAllByProjectIdOrderByStartDateDesc(projectEntity);

        boolean isReleaseAdded = releaseService.addRelease(releaseRequestDto);
        Assertions.assertThat(isReleaseAdded).isTrue();
        Mockito.verify(releaseRepository, Mockito.times(1))
                .save(releaseMapper.releaseDtoToEntity(releaseRequestDto));

    }

    @Test
    @DisplayName("Should return release info as string")
    void getReleaseString(){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        OffsetDateTime startTime = OffsetDateTime.now();
        OffsetDateTime finishTime = OffsetDateTime.now();
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setName("test");
        taskEntity.setCreateDate(startTime);
        taskEntity.setFinishDate(finishTime);
        ReleaseEntity releaseEntity = new ReleaseEntity();
        releaseEntity.setName("test");
        releaseEntity.setStartDate(startTime);
        releaseEntity.setFinishDate(finishTime);

        Mockito.doReturn(releaseEntity)
                .when(taskReleaseHistoryService)
                .getCurrentReleaseByTask(taskEntity);

        Mockito.doReturn(finishTime.format(dateFormatter))
                .when(dateTimeMapper)
                .dateToString(finishTime);

        String expectedResult = String.format("%s (%s - %s)",
                releaseEntity.getName(),
                startTime.format(dateFormatter),
                finishTime.format(dateFormatter));

        String result = releaseService.getReleaseString(taskEntity);
        Assertions.assertThat(result).isEqualTo(expectedResult);
        Mockito.verify(taskReleaseHistoryService, Mockito.times(1)).getCurrentReleaseByTask(taskEntity);
        Mockito.verify(dateTimeMapper, Mockito.times(2))
                .dateToString(ArgumentMatchers.any(OffsetDateTime.class));
    }


    @Test
    @DisplayName("Should return current release")
    void getCurrentRelease(){ // TODO: разобраться
        UUID uuid = UUID.randomUUID();
        String projectName = "testProjectName";
        ReleaseResponseDto releaseResponseDto = new ReleaseResponseDto();
        releaseResponseDto.setId(uuid.toString());
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(uuid);
        projectEntity.setName(projectName);
        ReleaseEntity releaseEntity = new ReleaseEntity();
        releaseEntity.setId(UUID.randomUUID());
        releaseEntity.setStartDate(OffsetDateTime.now());
        releaseEntity.setFinishDate(OffsetDateTime.now());
        List<ReleaseEntity> releaseEntities = Collections.singletonList(releaseEntity);

        Mockito.doReturn(projectEntity)
                .when(projectService)
                .getProjectEntity(projectName);

        Mockito.doReturn(Optional.of(releaseEntities))
                .when(releaseRepository)
                .findAllByProjectIdOrderByStartDateDesc(projectEntity);

        Mockito.doReturn(Optional.of(releaseEntity))
                .when(releaseRepository)
                .getCurrentRelease(uuid);

        Mockito.doReturn(releaseResponseDto)
                .when(releaseMapper)
                .releaseEntityToDto(ArgumentMatchers.any(ReleaseEntity.class));

        ReleaseResponseDto output = releaseService.getCurrentRelease(projectName);
        Assertions.assertThat(output).isNull();
    }
}