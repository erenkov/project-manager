package com.developing.simbir_product.service;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.ProjectResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;

import java.util.List;
import java.util.UUID;

public interface ProjectService {

    ProjectResponseDto getById(UUID id);

    ProjectResponseDto addProject(ProjectRequestDto projectRequestDto);

    ProjectResponseDto editProject(ProjectRequestDto projectRequestDto);

    void deleteById(UUID id);

    ProjectResponseDto findByName(String name);

    ProjectEntity getProjectEntity(String name);

    ProjectEntity addProjectEntity(ProjectEntity projectEntity);

    List<ProjectEntity> findAll();
}
