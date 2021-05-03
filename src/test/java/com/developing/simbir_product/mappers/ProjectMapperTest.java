package com.developing.simbir_product.mappers;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.ProjectResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.ProjectStatus;
import com.developing.simbir_product.entity.TeamEntity;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.TeamService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class ProjectMapperTest {

    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    ProjectEntity projectEntity;
    ProjectRequestDto projectDto;
    TeamEntity teamEntity;
    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    TeamService teamService;
    @Autowired
    ProjectService projectService;


    @BeforeEach
    void before() {
        teamEntity = new TeamEntity("team name", "description");
        teamEntity = teamService.addTeam(teamEntity);

        projectEntity = new ProjectEntity("project name",
                "description",
                ProjectStatus.BACKLOG,
                OffsetDateTime.now(),
                OffsetDateTime.now().plusMonths(1),
                OffsetDateTime.parse("2099-01-01T10:00:00+00:00"));
        projectEntity.setTeamId(teamEntity);
        projectEntity = projectService.addProjectEntity(projectEntity);

        projectDto = new ProjectRequestDto();
        projectDto.setName("project name");
        projectDto.setDescription("description");
        projectDto.setStartDate(dateToString(OffsetDateTime.now()));
        projectDto.setEstFinishDate(dateToString(OffsetDateTime.now().plusMonths(1)));
        projectDto.setTeamName("team name");
        projectDto.setStatus("BACKLOG");
    }

    @AfterEach
    void after() {
        projectService.deleteById(projectEntity.getId());
        teamService.deleteById(teamEntity.getId());
    }

    private String dateToString(OffsetDateTime dateTime) {
        return dateTime.format(dateFormatter);
    }

    @Test
    void projectEntityToDto() {
        ProjectResponseDto projectDtoTest = projectMapper.projectEntityToDto(projectEntity);
        assertEquals(projectDtoTest.getDescription(), projectEntity.getDescription());
        assertEquals(projectDtoTest.getName(), projectEntity.getName());
        assertEquals(projectDtoTest.getStartDate(), dateToString(projectEntity.getStartDate()));
        assertEquals(projectDtoTest.getEstFinishDate(), dateToString(projectEntity.getEstFinishDate()));
        assertEquals(projectDtoTest.getStatus(), projectEntity.getProjectStatus().name());
        assertEquals(projectDtoTest.getTeamName(), projectEntity.getTeamId().getName());
    }

    @Test
    void projectDtoToEntity() {
        ProjectEntity projectEntityTest = projectMapper.projectDtoToEntity(projectDto);
        assertEquals(projectEntityTest.getName(), projectDto.getName());
        assertEquals(projectEntityTest.getDescription(), projectDto.getDescription());
        assertEquals(projectEntityTest.getProjectStatus().name(), projectDto.getStatus());
        assertEquals(projectEntityTest.getTeamId().getName(), projectDto.getTeamName());
        assertEquals(dateToString(projectEntityTest.getEstFinishDate()), projectDto.getEstFinishDate());
        assertEquals(dateToString(projectEntityTest.getStartDate()), projectDto.getStartDate());
    }
}