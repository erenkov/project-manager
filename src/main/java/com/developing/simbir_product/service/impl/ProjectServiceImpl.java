package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.ProjectResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.ProjectStatus;
import com.developing.simbir_product.repository.ProjectRepository;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

        ProjectResponseDto responseDto = new ProjectResponseDto();
        //todo add mapper
        return responseDto;
    }

    @Transactional
    @Override
    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto) {
        ProjectEntity entity = new ProjectEntity("Проект-1",
                                              "Описание",
                                                        ProjectStatus.BACKLOG,
                                                        LocalDateTime.now(),
                                                        LocalDateTime.MAX,
                                                        LocalDateTime.MAX);
        projectRepository.save(entity);

        return entity;
    }

}
