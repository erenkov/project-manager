package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.TeamRequestDto;
import com.developing.simbir_product.entity.TeamEntity;
import com.developing.simbir_product.mappers.TeamMapper;
import com.developing.simbir_product.repository.TeamRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamMapper teamMapper;

    @InjectMocks
    private TeamServiceImpl teamService;

    @Test
    @DisplayName("Should find team by ID")
    void getById() {
        UUID uuid = UUID.randomUUID();
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(uuid);

        Mockito.doReturn(Optional.of(teamEntity))
                .when(teamRepository)
                .findById(uuid);

        TeamEntity outputTeamEntity = teamService.getById(uuid);
        Assertions.assertThat(outputTeamEntity.getId()).isEqualTo(teamEntity.getId());
        Mockito.verify(teamRepository, Mockito.times(1)).findById(uuid);
    }

    @Test
    @DisplayName("Should add team")
    void addTeam() {
        TeamEntity inputTeamEntity = new TeamEntity();

        TeamEntity outputTeamEntity = teamService.addTeam(inputTeamEntity);
        Mockito.verify(teamRepository, Mockito.times(1)).save(inputTeamEntity);
    }

    @Test
    @DisplayName("Should edit team if exists")
    void editTeam() {
        TeamRequestDto inputTeamRequestDto = new TeamRequestDto();
        inputTeamRequestDto.setName("test");
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName("test");

        Mockito.doReturn(teamEntity)
                .when(teamMapper)
                .teamDtoToEntity(inputTeamRequestDto);

        Mockito.doReturn(Optional.of(teamEntity))
                .when(teamRepository)
                .findByName(inputTeamRequestDto.getName());

        TeamEntity teamEntity1 = teamService.findByName(inputTeamRequestDto.getName());

        boolean isTeamEdited = teamService.editTeam(inputTeamRequestDto);
        Assertions.assertThat(isTeamEdited).isTrue();
        Assertions.assertThat(teamEntity1.getId()).isEqualTo(teamEntity.getId());
        Mockito.verify(teamRepository, Mockito.times(1)).save(teamEntity);
    }

    @Test
    @DisplayName("Shouldn't edit team if doesn't exist")
    void editTeamFalse(){
        TeamRequestDto inputTeamRequestDto = new TeamRequestDto();
        inputTeamRequestDto.setName("test");
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName("test");

        Mockito.doReturn(teamEntity)
                .when(teamMapper)
                .teamDtoToEntity(inputTeamRequestDto);

        boolean isTeamEdited = teamService.editTeam(inputTeamRequestDto);
        Assertions.assertThat(isTeamEdited).isFalse();
        Mockito.verify(teamRepository, Mockito.times(0)).save(ArgumentMatchers.any(TeamEntity.class));
    }
}