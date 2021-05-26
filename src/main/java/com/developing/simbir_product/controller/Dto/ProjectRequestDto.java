package com.developing.simbir_product.controller.Dto;

import com.developing.simbir_product.validators.ProjectWithDates;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Schema(description = "Проект")
@ProjectWithDates
public class ProjectRequestDto {

    @Schema(description = "Навзвание проекта")
    @NotBlank(message = "Name must not be empty")
    @Size(max = 50, message = "Name must not be greater than 50 characters")
    private String name;

    @Schema(description = "Описание проекта")
    private String description;

    @Schema(description = "Команда")
    @Size(max = 50, message = "Team name must not be greater than 50 characters")
    private String teamName;

    @Schema(description = "Статус")
    private String status;

    @Schema(description = "Дата начала проекта")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    @Schema(description = "Ориентировочная дата конца проекта")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime estFinishDate;


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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEstFinishDate() {
        return estFinishDate;
    }

    public void setEstFinishDate(LocalDateTime estFinishDate) {
        this.estFinishDate = estFinishDate;
    }
}
