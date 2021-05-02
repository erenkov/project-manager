package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.entity.TeamEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.repository.TeamRepository;
import com.developing.simbir_product.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

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
}
