package com.developing.simbir_product.mappers;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.ProjectResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.service.TaskService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Mapper(uses = {DateTimeMapper.class, TeamMapper.class}, componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ProjectMapper {

    @Autowired
    private TaskService taskService;


    @Mapping(target = "teamName", source = "teamId.name")
    @Mapping(target = "status", source = "projectStatus")
    public abstract ProjectResponseDto projectEntityToDto(ProjectEntity projectEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "finishDate", ignore = true)
    @Mapping(target = "tasks", source = "name")
    @Mapping(target = "teamId", source = "teamName")
    @Mapping(target = "projectStatus", source = "status")
    public abstract ProjectEntity projectDtoToEntity(ProjectRequestDto projectRequestDto);

    public List<TaskEntity> tasksOfProject(String projectName) {
        return taskService.getTasksByProjectsName(projectName);
    }
}
