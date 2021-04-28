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

    //todo !!!!!!!!!!!!1 Похоже что при последнем коммите пропал конструктор из ProjectEntity !!!!!!!!!!!
    // + Я не знаю что должен возвращать этот метод
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

    //todo !!!!!!!!!! Я так понял что и обновление и сохранение этим методом !!!!!!!!!!!!!!!!!!
    @Transactional
    @Override
    public void deleteById(UUID id) {
         projectRepository.deleteById(id);
    }
}
