package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.TeamRequestDto;
import com.developing.simbir_product.controller.Dto.TeamResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.TeamEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.mappers.TeamMapper;
import com.developing.simbir_product.repository.TeamRepository;
import com.developing.simbir_product.service.TeamService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMapper teamMapper;

    @Transactional
    @Override
    public TeamEntity addTeam(TeamEntity teamEntity) {
        return teamRepository.save(teamEntity);
    }

    @Transactional
    @Override
    public boolean editTeam(TeamRequestDto teamRequestDto) {
        TeamEntity teamEntity = teamMapper.teamDtoToEntity(teamRequestDto);
//        Optional<TeamEntity> tempProjectFromDB = Optional.ofNullable(findByName(teamEntity.getName())); - старая реализация

        Optional<TeamEntity> teamEntity1 = teamRepository.findByName(teamEntity.getName());
        if (teamEntity1.isEmpty()) { // Если при редактировании текущая команда в БД не найдена,
            return false;                  // то не выполняем запись в БД
        }

        teamEntity.setId(teamEntity1.get().getId());
        teamRepository.save(teamEntity);
        return true;
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        teamRepository.deleteById(id);
    }

    @Transactional
    @Override
    public TeamEntity findByName(String name) {
        return teamRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Team with name = '%s' not found", name)));
    }

    @Override
    public String getTeamName(TaskEntity taskEntity) {
        ProjectEntity projectEntity = taskEntity.getProjectId();
        if (projectEntity.getTeamId() == null) {
            return "";
        }
        return projectEntity.getTeamId().getName();
    }

    @Override
    public List<String> getListOfAllTeamNames() {
        return teamRepository.findAll().stream().map(TeamEntity::getName).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllTeamNames() {
        return teamRepository.findAll().stream().map(TeamEntity::getName).collect(Collectors.toList());
    }

    @Override
    public TeamEntity mapTeamByName(String teamName) {
        if (StringUtils.isEmpty(teamName)) {
            return null;
        }
        return findByName(teamName);
    }

    @Transactional
    @Override
    public boolean addTeamDto(TeamRequestDto teamRequestDto) {
        Optional<TeamEntity> teamFromDb = teamRepository.findByName(teamRequestDto.getName());

        if (teamFromDb.isPresent()) { // Если при добавлении команды в БД уже найдена команда с
            return false;             // тем же именем, то не записываем команду в БД
        }

        teamRepository.save(teamMapper.teamDtoToEntity(teamRequestDto));
        return true;
    }

    @Transactional
    @Override
    public TeamResponseDto findDtoByName(String name) {
        TeamEntity teamEntity = teamRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Team with name = '%s' not found", name)));
        return teamMapper.teamEntityToDto(teamEntity);
    }
}
