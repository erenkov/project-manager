package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.ProjectResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.ProjectStatus;
import com.developing.simbir_product.mappers.ProjectMapperImpl;
import com.developing.simbir_product.repository.ProjectRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.parameters.P;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private ProjectMapperImpl projectMapper;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Captor
    ArgumentCaptor<ProjectResponseDto> projectResponseDtoArgumentCaptor;

    @Test
    @DisplayName("Should find project by ID")
    void should_find_project_by_id() {
        UUID uuid = UUID.randomUUID();
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(uuid);
        projectEntity.setName("testProject");
        projectEntity.setDescription("Test project");

        ProjectResponseDto expectedProjectResponseDto = new ProjectResponseDto();
        expectedProjectResponseDto.setName("testProject");
        expectedProjectResponseDto.setDescription("Test project");

        Mockito.when(projectRepository.findById(uuid)).thenReturn(Optional.of(projectEntity));
        Mockito.when(projectMapper.projectEntityToDto(Mockito.any(ProjectEntity.class))).thenReturn(expectedProjectResponseDto);

        ProjectResponseDto actualProjectResponseDto = projectService.getById(uuid);

        Assertions.assertThat(actualProjectResponseDto.getName()).isEqualTo(expectedProjectResponseDto.getName());
        Assertions.assertThat(actualProjectResponseDto.getDescription()).isEqualTo(expectedProjectResponseDto.getDescription());


    }

    @Test
    @DisplayName("Should add projects")
    void should_add_project() {

        ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        ProjectEntity projectEntity = new ProjectEntity();
        ProjectResponseDto expectedProjectResponseDto = new ProjectResponseDto();
        projectEntity.setProjectStatus(ProjectStatus.BACKLOG);
        projectRequestDto.setStatus("BACKLOG");
        expectedProjectResponseDto.setStatus("BACKLOG");

        Mockito.doReturn(projectEntity)
                .when(projectMapper)
                .projectDtoToEntity(ArgumentMatchers.any(ProjectRequestDto.class));

        Mockito.doReturn(expectedProjectResponseDto)
                .when(projectMapper)
                .projectEntityToDto(projectEntity);

        ProjectResponseDto actualProjectResponseDto = projectService.addProject(projectRequestDto);

        Assertions.assertThat(expectedProjectResponseDto.getStatus()).isEqualTo(actualProjectResponseDto.getStatus());

        Mockito.verify(projectMapper, Mockito.times(1))
                .projectEntityToDto(ArgumentMatchers.any(ProjectEntity.class));
        Mockito.verify(projectMapper, Mockito.times(1))
                .projectDtoToEntity(ArgumentMatchers.any(ProjectRequestDto.class));
        Mockito.verify(projectRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(ProjectEntity.class));
    }
}