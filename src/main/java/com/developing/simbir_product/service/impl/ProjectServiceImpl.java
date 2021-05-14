package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.ProjectResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.ProjectStatus;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.mappers.ProjectMapper;
import com.developing.simbir_product.repository.ProjectRepository;
import com.developing.simbir_product.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class ProjectServiceImpl implements ProjectService {
    Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    @Override
    public ProjectResponseDto getById(UUID id) {

        ProjectEntity projectEntity = projectRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Project with ID = '%s' not found", id)));

        return projectMapper.projectEntityToDto(projectEntity);
    }


    @Transactional
    @Override
    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto) {

        projectRequestDto.setStatus(ProjectStatus.BACKLOG.toString());

        ProjectEntity projectEntity = projectMapper.projectDtoToEntity(projectRequestDto);

        projectRepository.save(projectEntity);

        ProjectResponseDto projectResponseDto = projectMapper.projectEntityToDto(projectEntity);
        logger.trace("{} project has been created", projectRequestDto.getName());
        return projectResponseDto; //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Transactional
    @Override
    public ProjectEntity addProjectEntity(ProjectEntity projectEntity) {
        return projectRepository.save(projectEntity);
    }

    @Transactional
    @Override
    public ProjectResponseDto editProject(ProjectRequestDto projectRequestDto) {

        ProjectEntity projectEntity = projectMapper.projectDtoToEntity(projectRequestDto);
        ProjectEntity tempProjectFromDB = getProjectEntity(projectEntity.getName());
        projectEntity.setId(tempProjectFromDB.getId());
        projectEntity.setFinishDate(tempProjectFromDB.getEstFinishDate());
        logger.trace(projectRequestDto.getName() + " has been edited");
        return projectMapper.projectEntityToDto(projectRepository.save(projectEntity));
    }


    @Transactional
    @Override
    public void deleteById(UUID id) {
        projectRepository.deleteById(id);
        //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
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
                () -> new NotFoundException(String.format("Project with name = '%s' not found", name)));
    }

    @Transactional
    @Override
    public List<ProjectResponseDto> findAll() {
        return projectRepository
                .findAll()
                .stream()
                .map(pE -> projectMapper.projectEntityToDto(pE))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<String> getListOfAllProjectNames() {
        return projectRepository.findAll().stream().map(ProjectEntity::getName).collect(Collectors.toList());
    }

    @Override
    public List<String> getListOfAllProjectStatus() {
        return Arrays.stream(ProjectStatus.values()).map(ProjectStatus::toString).collect(Collectors.toList());
    }
}
