package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.entity.TeamEntity;
import com.developing.simbir_product.exception.NotFoundException;
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
        return teamRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Team with ID = ' ' not found")
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

    @Override
    public TeamEntity findByName(String name) {
        return teamRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("Team with name = ' ' not found")
        ); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }
}
