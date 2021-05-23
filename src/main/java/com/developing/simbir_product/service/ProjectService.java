package com.developing.simbir_product.service;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.ProjectResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;

import java.util.List;
import java.util.UUID;


public interface ProjectService {

    boolean addProject(ProjectRequestDto projectRequestDto);

    boolean editProject(ProjectRequestDto projectRequestDto);

    void deleteById(UUID id);

    ProjectResponseDto findByName(String name);

    ProjectEntity getProjectEntity(String name);

    ProjectEntity addProjectEntity(ProjectEntity projectEntity);

    List<String> getListOfAllProjectNames();

    List<String> getListOfAllProjectStatus();

    List<String> getListOfAllProjectNamesByTeam(String teamName);

}
