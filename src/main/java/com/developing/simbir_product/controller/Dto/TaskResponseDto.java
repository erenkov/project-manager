package com.developing.simbir_product.controller.Dto;

import com.developing.simbir_product.entity.TaskStatus;
import com.developing.simbir_product.entity.TaskType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;


@Schema(description = "Задача")
public class TaskResponseDto {

    @Schema(description = "ID задачи")
    private UUID id;

    @Schema(description = "Название задачи")
    private String name;

    @Schema(description = "Состояние задачи")
    private TaskStatus taskStatus;

    @Schema(description = "ID типа задачи")
    private TaskType taskType;

    @Schema(description = "ID проекта")
    private ProjectResponseDto projectDto;

    @Schema(description = "Описание задачи")
    private String description;

    @Schema(description = "Дата создания")
    private LocalDateTime createDate;

    @Schema(description = "Ориентировочная дата завершения")
    private LocalDateTime dueDate;

    @Schema(description = "Дата завершения")
    private LocalDateTime finishDate;

    @Schema(description = "Ориентировочные затраты")
    private int estCosts;

    @Schema(description = "Реальные затраты")
    private int actualCosts;

    @Schema(description = "Комментарии")
    private String comments;

    @Schema(description = "Приоритет")
    private int priority;

    @Schema(description = "Исполнитель")
    private UserResponseDto userResponseDto;

    @Schema(description = "Команда")
    private TeamResponseDto teamDto;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public ProjectResponseDto getProjectDto() {
        return projectDto;
    }

    public void setProjectDto(ProjectResponseDto projectDto) {
        this.projectDto = projectDto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public int getEstCosts() {
        return estCosts;
    }

    public void setEstCosts(int estCosts) {
        this.estCosts = estCosts;
    }

    public int getActualCosts() {
        return actualCosts;
    }

    public void setActualCosts(int actualCosts) {
        this.actualCosts = actualCosts;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }

    public TeamResponseDto getTeamDto() {
        return teamDto;
    }

    public void setTeamDto(TeamResponseDto teamDto) {
        this.teamDto = teamDto;
    }
}
