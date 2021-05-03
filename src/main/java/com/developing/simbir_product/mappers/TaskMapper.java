package com.developing.simbir_product.mappers;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.controller.Dto.TaskResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.service.TaskReleaseHistoryService;
import com.developing.simbir_product.service.UserTaskHistoryService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Mapper(uses = DateTimeMapper.class, componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class TaskMapper {

    @Autowired
    DateTimeMapper dateTimeMapper;

    @Autowired
    private UserTaskHistoryService userTaskHistoryService;

    @Autowired
    private TaskReleaseHistoryService taskReleaseHistoryService;

    @Autowired
    private ProjectService projectService;


    @Mapping(target = "status", source = "taskStatus")
    @Mapping(target = "type", source = "taskType")
    @Mapping(target = "project", source = "projectId.name")
    @Mapping(target = "assigneeName", source = ".", qualifiedByName = "assigneeByTask")
    @Mapping(target = "release", source = ".", qualifiedByName = "releaseByTask")
    @Mapping(target = "team", source = ".", qualifiedByName = "teamByTask")
    public abstract TaskResponseDto taskEntityToDto(TaskEntity taskEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "projectId", source = "project")
    @Mapping(target = "taskType", source = "type")
    @Mapping(target = "taskStatus", source = "status")
    public abstract TaskEntity taskDtoToEntity(TaskRequestDto taskRequestDto);


    @Named("assigneeByTask")
    public String assigneeByTask(TaskEntity taskEntity) {
        UserEntity assignee = getUserEntity(taskEntity);
        return String.format("%s %s %s", assignee.getFirstName(), assignee.getFirstName(), assignee.getUserNumber());
    }

    @Named("releaseByTask")
    public String releaseByTask(TaskEntity taskEntity) {
        ReleaseEntity release = taskReleaseHistoryService.getCurrentReleaseByTask(taskEntity);
        return String.format("%s (%s - %s)",
                release.getName(),
                dateTimeMapper.dateToString(release.getStartDate()),
                dateTimeMapper.dateToString(release.getFinishDate()));
    }

    @Named("teamByTask")
    public String teamByTask(TaskEntity taskEntity) {
        return getUserEntity(taskEntity).getTeamId().getName();
    }

    public ProjectEntity projectByName(String projectName) {
        return projectService.getProjectEntity(projectName);
    }

    private UserEntity getUserEntity(TaskEntity taskEntity) {
        return userTaskHistoryService.getCurrentUserByTask(taskEntity);
    }
}
