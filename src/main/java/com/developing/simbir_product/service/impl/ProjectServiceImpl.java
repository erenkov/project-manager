package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.ProjectResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.ProjectStatus;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.exception.ProjectAlreadyExistException;
import com.developing.simbir_product.mappers.ProjectMapper;
import com.developing.simbir_product.repository.ProjectRepository;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.TeamService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ProjectServiceImpl implements ProjectService {

    private final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MessageSource messageSource;

    @Override
    @Transactional
    public boolean addProject(ProjectRequestDto projectRequestDto) {
        if (projectRequestDto == null) {
            throw new IllegalArgumentException(messageSource.getMessage("projectService.addProject.illegalArgument",
                    null, Locale.getDefault()));
        }
        String projectName = projectRequestDto.getName();
        if (isProjectExist(projectName)) {
            throw new ProjectAlreadyExistException("projectAlreadyExist.message", projectRequestDto, messageSource);
        }
        projectRequestDto.setStatus(ProjectStatus.BACKLOG.name());
        ProjectEntity projectEntity = projectMapper.projectDtoToEntity(projectRequestDto);
        projectRepository.save(projectEntity);
        logger.info(messageSource.getMessage("projectService.addProject.logger", null, Locale.getDefault()),
                projectName);
        return true;
    }

    @Transactional
    @Override
    public ProjectEntity addProjectEntity(ProjectEntity projectEntity) {
        return projectRepository.save(projectEntity);
    }

    @Transactional
    @Override
    public boolean editProject(ProjectRequestDto projectRequestDto) {
        String projectName = projectRequestDto.getName();
        if (!isProjectExist(projectName)) {
            throw new NotFoundException(messageSource.getMessage("projectService.notFound",
                    new String[]{projectName}, LocaleContextHolder.getLocale()));
        }
        ProjectEntity projectEntity = projectMapper.projectDtoToEntity(projectRequestDto);
        ProjectEntity tempProjectFromDB = getProjectEntity(projectEntity.getName());
        projectEntity.setId(tempProjectFromDB.getId());
        projectEntity.setFinishDate(tempProjectFromDB.getEstFinishDate());
        projectRepository.save(projectEntity);
        logger.info(messageSource.getMessage("projectService.editProject.logger", null, Locale.getDefault()),
                projectName);
        return true;
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        projectRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ProjectResponseDto findByName(String name) {
        return projectMapper.projectEntityToDto(getProjectEntity(name));
    }

    @Transactional
    @Override
    public ProjectEntity getProjectEntity(String name) {
        return projectRepository.findByName(name).orElseThrow(
                () -> new NotFoundException(messageSource.getMessage("projectService.notFound",
                        new String[]{name}, LocaleContextHolder.getLocale())));
    }

    @Transactional
    @Override
    public List<String> getListOfAllProjectNames() {
        return projectRepository.findAllByOrderByNameAsc()
                .stream()
                .sorted(Comparator.comparing(ProjectEntity::getProjectStatus))
                .map(ProjectEntity::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getListOfAllProjectStatus() {
        return Arrays.stream(ProjectStatus.values()).map(ProjectStatus::name).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<String> getListOfAllProjectNamesByTeam(String teamName) {
        if (StringUtils.isEmpty(teamName)) {
            return Collections.emptyList();
        }
        return projectRepository
                .findAllByTeamIdOrderByNameAsc(teamService.findByName(teamName))
                .stream()
                .sorted(Comparator.comparing(ProjectEntity::getProjectStatus))
                .map(ProjectEntity::getName)
                .sorted()
                .collect(Collectors.toList());
    }

    private boolean isProjectExist(String name) {
        return projectRepository.findByName(name).isPresent();
    }
}
