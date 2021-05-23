package com.developing.simbir_product.service;

import com.developing.simbir_product.controller.Dto.TeamRequestDto;
import com.developing.simbir_product.controller.Dto.TeamResponseDto;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.TeamEntity;

import java.util.List;
import java.util.UUID;


public interface TeamService {

    TeamEntity addTeam(TeamEntity teamRequestDto);

    boolean addTeamDto(TeamRequestDto teamRequestDto);

    boolean editTeam(TeamRequestDto teamRequestDto);

    void deleteById(UUID id);

    TeamEntity findByName(String name);

    String getTeamName(TaskEntity taskEntity);

    List<String> findAllTeamNames();

    List<String> getListOfAllTeamNames();

    TeamEntity mapTeamByName(String teamName);

    TeamResponseDto findDtoByName(String name);
}
