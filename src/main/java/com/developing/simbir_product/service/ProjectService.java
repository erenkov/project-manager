package com.developing.simbir_product.service;

import com.developing.simbir_product.dto.ProjectRequestDto;
import com.developing.simbir_product.dto.ProjectResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;

import java.util.UUID;

public interface ProjectService {

    ProjectEntity getById(UUID id);

    void addProject();

    ProjectResponseDto editProject(ProjectRequestDto projectRequestDto);

    void deleteById(UUID id);

    ProjectEntity findByName(String name);
}
