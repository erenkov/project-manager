package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.TeamEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.repository.TeamRepository;
import com.developing.simbir_product.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Transactional
    @Override
    public TeamEntity getById(UUID id) {
        return teamRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Team with ID = '%s' not found", id))
        ); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Transactional
    @Override
    public TeamEntity addTeam(TeamEntity teamEntity) {
        return teamRepository.save(teamEntity); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Transactional
    @Override
    public TeamEntity editTeam(TeamEntity teamEntity) {
        return teamRepository.save(teamEntity); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        teamRepository.deleteById(id); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Transactional
    @Override
    public TeamEntity findByName(String name) {
        return teamRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Team with name = '%s' not found", name)));
        //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Override
    public String getTeamName(TaskEntity taskEntity) {
        ProjectEntity projectEntity = taskEntity.getProjectId();
        if (projectEntity.getTeamId() == null) {
            return "";
        }
        return projectEntity.getTeamId().getName();
    }

    public List <String> getListOfAllTeamNames() {
        return teamRepository.findAll().stream().map(TeamEntity::getName).collect(Collectors.toList());
    }

    @Override
    public TeamEntity mapTeamByName(String teamName) {
        if (teamName.isEmpty()) {
            return null;
        }
        return findByName(teamName);
    }

    @Override
    public UUID getUuidFromString(String stringUuid) {
        if (stringUuid == null || stringUuid.isEmpty()) {
            return null;
        }
        return UUID.fromString(stringUuid);
    }
}
