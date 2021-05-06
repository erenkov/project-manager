package com.developing.simbir_product.mappers;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.controller.Dto.TaskResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.ReleaseService;
import com.developing.simbir_product.service.TeamService;
import com.developing.simbir_product.service.UserService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(uses = DateTimeMapper.class, componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class TaskMapper {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private ReleaseService releaseService;


    @Mapping(target = "status", source = "taskStatus")
    @Mapping(target = "type", source = "taskType")
    @Mapping(target = "projectName", source = "projectId.name")
    @Mapping(target = "assigneeName", source = ".", qualifiedByName = "assigneeByTask")
    @Mapping(target = "release", source = ".", qualifiedByName = "releaseByTask")
    @Mapping(target = "team", source = ".", qualifiedByName = "teamByTask")
    public abstract TaskResponseDto taskEntityToDto(TaskEntity taskEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "projectId", source = "projectName")
    @Mapping(target = "taskType", source = "type")
    @Mapping(target = "taskStatus", source = "status")
    public abstract TaskEntity taskDtoToEntity(TaskRequestDto taskRequestDto);


    @Named("assigneeByTask")
    public String assigneeByTask(TaskEntity taskEntity) {
        return userService.getUserNameAndNumber(taskEntity);
    }

    @Named("releaseByTask")
    public String releaseByTask(TaskEntity taskEntity) {
        return releaseService.getReleaseString(taskEntity);
    }

    @Named("teamByTask")
    public String teamByTask(TaskEntity taskEntity) {
        return teamService.getTeamName(taskEntity);
    }

    public ProjectEntity projectByName(String projectName) {
        return projectService.getProjectEntity(projectName);
    }
}
