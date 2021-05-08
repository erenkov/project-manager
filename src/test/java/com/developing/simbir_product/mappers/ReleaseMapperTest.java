package com.developing.simbir_product.mappers;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import com.developing.simbir_product.controller.Dto.ReleaseResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.ProjectStatus;
import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.entity.TeamEntity;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.ReleaseService;
import com.developing.simbir_product.service.TeamService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class ReleaseMapperTest {

    ReleaseRequestDto releaseDto;
    ReleaseEntity releaseEntity;
    TeamEntity teamEntity;
    ProjectEntity projectEntity;

    @Autowired
    ReleaseMapper releaseMapper;
    @Autowired
    ReleaseService releaseService;
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
                null);
        projectEntity.setTeamId(teamEntity);
        projectEntity = projectService.addProjectEntity(projectEntity);

        releaseEntity = new ReleaseEntity("release name",
                OffsetDateTime.now().minusMonths(1),
                OffsetDateTime.now().plusMonths(2));
        releaseEntity.setProjectId(projectEntity);
        releaseEntity = releaseService.addReleaseEntity(releaseEntity);

        releaseDto = new ReleaseRequestDto();
        releaseDto.setName("release name");
        releaseDto.setFinishDate(LocalDateTime.now().plusMonths(1));
        releaseDto.setStartDate(LocalDateTime.now().minusMonths(1));
        releaseDto.setProjectName("project name");
    }

    @AfterEach
    void after() {
        releaseService.deleteById(releaseEntity.getId());
    }


    @Test
    void releaseEntityToDto() {
        ReleaseResponseDto releaseDtoTest = releaseMapper.releaseEntityToDto(releaseEntity);
        assertEquals(releaseEntity.getName(), releaseDtoTest.getName());
        assertEquals(releaseEntity.getFinishDate().toLocalDateTime(), releaseDtoTest.getFinishDate());
        assertEquals(releaseEntity.getStartDate().toLocalDateTime(), releaseDtoTest.getStartDate());
        assertEquals(releaseEntity.getProjectId().getName(), releaseDtoTest.getProjectName());
    }

    @Test
    void releaseDtoToEntity() {
        ReleaseEntity releaseEntityTest = releaseMapper.releaseDtoToEntity(releaseDto);
        assertEquals(releaseDto.getName(), releaseEntityTest.getName());
        assertEquals(releaseDto.getFinishDate(), releaseEntityTest.getFinishDate().toLocalDateTime());
        assertEquals(releaseDto.getStartDate(), releaseEntityTest.getStartDate().toLocalDateTime());
        assertEquals(releaseDto.getProjectName(), releaseEntityTest.getProjectId().getName());
    }
}