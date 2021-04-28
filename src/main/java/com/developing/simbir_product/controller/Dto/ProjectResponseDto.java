package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Проект")
public class ProjectResponseDto {

    @Schema(description = "Навзвание проекта")
    private String name;

    @Schema(description = "Описание проекта")
    private String description;

    @Schema(description = "Команда")
    private String teamName;

    @Schema(description = "Статус")
    private String status;

    @Schema(description = "Дата начала проекта")
    private String startDate;

    @Schema(description = "Ориентировочная дата конца проекта")
    private String estFinishDate;


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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEstFinishDate() {
        return estFinishDate;
    }

    public void setEstFinishDate(String estFinishDate) {
        this.estFinishDate = estFinishDate;
    }
}
