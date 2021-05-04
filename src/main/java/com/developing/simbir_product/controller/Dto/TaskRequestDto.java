package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Schema(description = "Задача")
public class TaskRequestDto {

    @Schema(description = "Название задачи")
    @NotBlank
    @Size(max = 50)
    private String name;

    @Schema(description = "Статус задачи")
    private String status;

    @Schema(description = "Тип задачи")
    private String type;

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
    @Size(max = 50)
    private String assigneeName;

    @Schema(description = "Команда")
    @Size(max = 50)
    private String team;

    @Schema(description = "Релиз")
    @Size(max = 50)
    private String release;

    @Schema(description = "Проект")
    @Size(max = 50)
    private String project;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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
