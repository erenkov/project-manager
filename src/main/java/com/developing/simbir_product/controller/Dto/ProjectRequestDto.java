package com.developing.simbir_product.controller.Dto;

import com.developing.simbir_product.entity.ProjectStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;


@Schema(description = "Проект")
public class ProjectRequestDto {

    @Schema(description = "ID проекта")
    private UUID id;

    @Schema(description = "Навзвание проекта")
    private String name;

    @Schema(description = "Описание проекта")
    private String description;

    @Schema(description = "Команда")
    private TeamResponseDto teamDto;

    @Schema(description = "Статус")
    private ProjectStatus status;

    @Schema(description = "Дата начала проекта")
    private LocalDateTime startDate;

    @Schema(description = "Дата конца проекта")
    private LocalDateTime finishDate;

    @Schema(description = "Ориентировочная дата конца проекта")
    private LocalDateTime estFinishDate;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TeamResponseDto getTeamDto() {
        return teamDto;
    }

    public void setTeamDto(TeamResponseDto teamDto) {
        this.teamDto = teamDto;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public LocalDateTime getEstFinishDate() {
        return estFinishDate;
    }

    public void setEstFinishDate(LocalDateTime estFinishDate) {
        this.estFinishDate = estFinishDate;
    }
}
