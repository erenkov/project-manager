package com.developing.simbir_product.service;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.entity.TeamEntity;

import java.util.UUID;

public interface TeamService {

    //todo все сервисы возвращают Entity а не DTO
    TeamEntity getById(UUID id);

    void addTeam();

    TeamEntity editTeam(ProjectRequestDto projectRequestDto);

    void deleteById(UUID id);
}
