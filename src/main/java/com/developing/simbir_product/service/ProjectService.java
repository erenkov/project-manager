package com.developing.simbir_product.service;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.ProjectResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface ProjectService {

    ProjectEntity getById(UUID id);

    void addProject();

    ProjectResponseDto editProject(ProjectRequestDto projectRequestDto);

    void deleteById(UUID id);

    ProjectEntity findByName(String name);
}
