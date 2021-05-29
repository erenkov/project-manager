package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Schema(description = "Задача")
public class TaskResponseDto {

    @Schema(description = "ID задачи")
    private String id;

    @Schema(description = "Название задачи")
    private String name;

    @Schema(description = "Статус задачи")
    private String status;

    @Schema(description = "Тип задачи")
    private String type;

    @Schema(description = "Описание задачи")
    private String description;

    @Schema(description = "Дата создания")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime createDate;

    @Schema(description = "Ориентировочная дата завершения")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dueDate;

    @Schema(description = "Дата завершения")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime finishDate;

    @Schema(description = "Ориентировочные затраты")
    private Integer estCosts;

    @Schema(description = "Реальные затраты")
    private Integer actualCosts;

    @Schema(description = "Комментарии")
    private String comments;

    @Schema(description = "Приоритет")
    private Integer priority;

    @Schema(description = "Исполнитель")
    private String assigneeName;

    @Schema(description = "Команда")
    private String team;

    @Schema(description = "Релиз")
    private String release;

    @Schema(description = "Название проекта")
    private String projectName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getEstCosts() {
        return estCosts;
    }

    public void setEstCosts(Integer estCosts) {
        this.estCosts = estCosts;
    }

    public Integer getActualCosts() {
        return actualCosts;
    }

    public void setActualCosts(Integer actualCosts) {
        this.actualCosts = actualCosts;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
}
