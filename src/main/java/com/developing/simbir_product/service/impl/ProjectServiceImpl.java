package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.ProjectResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.repository.ProjectRepository;
import com.developing.simbir_product.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    private ProjectRepository projectRepository;


    @Transactional
    @Override
    public ProjectResponseDto getById(UUID id) {

        ProjectEntity projectEntity = projectRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Project with ID = ' ' not found")
        );

        ProjectResponseDto projectResponseDto = new ProjectResponseDto();

        //todo ProjectResponseDto = mapFrom projectEntity !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        return projectResponseDto;
    }


    @Transactional
    @Override
    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto) {

        ProjectEntity projectEntity = new ProjectEntity();

        //todo projectEntity = mapFrom projectRequestDto ???????????????????????????????????????

        projectRepository.save(projectEntity);

        return new ProjectResponseDto(); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }


    @Transactional
    @Override
    public ProjectResponseDto editProject(ProjectRequestDto projectRequestDto) {

        ProjectEntity projectEntity = new ProjectEntity();

        //todo projectEntity = mapFrom projectRequestDto ???????????????????????????????????

        projectRepository.save(projectEntity);

        return new ProjectResponseDto(); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }


    @Transactional
    @Override
    public void deleteById(UUID id) {
        projectRepository.deleteById(id); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }


    @Override
    public ProjectResponseDto findByName(String name) {
        ProjectEntity projectEntity = projectRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("Project with name = ' ' not found")
        );

        ProjectResponseDto projectResponseDto = new ProjectResponseDto();

        //todo ProjectResponseDto = mapFrom projectEntity !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        return projectResponseDto;
    }
}
