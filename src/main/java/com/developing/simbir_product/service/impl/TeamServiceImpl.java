package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.entity.TeamEntity;
import com.developing.simbir_product.repository.TeamRepository;
import com.developing.simbir_product.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Transactional
    @Override
    public TeamEntity getById(UUID id) {
        return teamRepository.findById(id).orElse(new TeamEntity());
    }

    @Transactional
    @Override
    public void addTeam() {

        TeamEntity teamEntity = new TeamEntity(
                "Team-1",
                "Desc team-1"
        );

        teamRepository.save(teamEntity);

//        return teamEntity;
    }

    @Transactional
    @Override
    public TeamEntity editTeam(ProjectRequestDto projectRequestDto) {

        TeamEntity teamEntity = teamRepository.findByName("Team-1").orElse(new TeamEntity());
        teamEntity.setDescription("new desc"); // Изменяем описание

        teamRepository.save(teamEntity);

        return teamEntity;
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        teamRepository.deleteById(id);
    }
}
