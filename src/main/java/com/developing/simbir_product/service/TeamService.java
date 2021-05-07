package com.developing.simbir_product.service;

import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.TeamEntity;

import java.util.List;
import java.util.UUID;

public interface TeamService {

    TeamEntity getById(UUID id);

    TeamEntity addTeam(TeamEntity teamRequestDto);

    TeamEntity editTeam(TeamEntity teamRequestDto);

    void deleteById(UUID id);

    TeamEntity findByName(String name);

    String getTeamName(TaskEntity taskEntity);

    public List<String> getListOfAllTeamNames();

}
