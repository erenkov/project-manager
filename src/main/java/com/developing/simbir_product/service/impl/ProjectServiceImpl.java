package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.ProjectResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.ProjectStatus;
import com.developing.simbir_product.repository.ProjectRepository;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Random;
import java.util.UUID;


@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TeamService teamService;

//    @Transactional
//    @Override
//    public ProjectResponseDto getById(UUID id) {
//        ProjectEntity projectEntity = projectRepository.findById(id).orElseThrow(
//                () -> new NotFoundException("Project with ID = ' ' not found")      //todo Свой exception
//        );
//
//        ProjectResponseDto responseDto = new ProjectResponseDto();
//        //todo add mapper
//        responseDto.setName(projectEntity.getName());
//        responseDto.setDescription(projectEntity.getDescription());
//        responseDto.setStartDate(projectEntity.getStartDate().toString());
//        responseDto.setEstFinishDate(projectEntity.getEstFinishDate().toString());
//        responseDto.setStatus("BACKLOG");
//        responseDto.setTeamName(projectEntity.getTeamId().toString());
//
//        return responseDto;
//    }


    @Transactional
    @Override
    public ProjectEntity getById(UUID id) {
        return projectRepository.getOne(id);
    }


    @Transactional
    @Override
    public void addProject() {

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
                "Проект-1" + new Random().nextInt(),
                "Описание",
                ProjectStatus.BACKLOG,
                OffsetDateTime.now(),
                OffsetDateTime.now(),
                OffsetDateTime.now());


        projectEntity.setTeamId(teamService.getByName("Team-2"));

        projectRepository.save(projectEntity);
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
