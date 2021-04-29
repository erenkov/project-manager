package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.ProjectResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.ProjectStatus;
import com.developing.simbir_product.entity.TeamEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.repository.ProjectRepository;
import com.developing.simbir_product.service.ProjectService;
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
                () -> new NotFoundException("Project with ID = ' ' not found")      //todo Свой exception
        );

        ProjectResponseDto responseDto = new ProjectResponseDto();
        //todo add mapper
        responseDto.setName(projectEntity.getName());
        responseDto.setDescription(projectEntity.getDescription());
        responseDto.setStartDate(projectEntity.getStartDate().toString());
        responseDto.setEstFinishDate(projectEntity.getEstFinishDate().toString());
        responseDto.setStatus("BACKLOG");
        responseDto.setTeamName(projectEntity.getTeamId().toString());

        return responseDto;
    }

    @Transactional
    @Override
    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto) {

//        ProjectEntity projectEntity = new ProjectEntity();
//
//        projectEntity.setName(projectRequestDto.getName());
//        projectEntity.setDescription(projectRequestDto.getDescription());
//        projectEntity.setProjectStatus(ProjectStatus.BACKLOG);
//        projectEntity.setTeamId(new TeamEntity()); //todo ????????
//        projectEntity.setStartDate(LocalDateTime.now());
//        projectEntity.setEstFinishDate(LocalDateTime.MAX);
//        projectEntity.setFinishDate(LocalDateTime.MAX);

        ProjectEntity projectEntity = new ProjectEntity(
                "Проект-1",
                "Описание",
                ProjectStatus.BACKLOG,
                LocalDateTime.now(),
                LocalDateTime.MAX,
                LocalDateTime.MAX);


        projectEntity.setTeamId(new TeamEntity("Team-1", "Desc team-1"));

        projectRepository.save(projectEntity);

        return new ProjectResponseDto();
    }

    @Transactional
    @Override
    public ProjectResponseDto editProject(ProjectRequestDto projectRequestDto) {

//        ProjectEntity entity = new ProjectEntity(
//                "Проект-1",
//                "Описание",
//                ProjectStatus.BACKLOG,
//                LocalDateTime.now(),
//                LocalDateTime.MAX,
//                LocalDateTime.MAX);

        ProjectEntity projectEntity = projectRepository.findByName("Проект-1").orElse(new ProjectEntity()); //todo
        projectEntity.setProjectStatus(ProjectStatus.DONE);         // Меняем статус проекта

        projectRepository.save(projectEntity);

        return new ProjectResponseDto();
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        projectRepository.deleteById(id);
    }
}
