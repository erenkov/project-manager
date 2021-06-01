package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Schema(description = "{projectDto.schema}")
public class ProjectResponseDto {

    @Schema(description = "{projectDto.name.schema}")
    private String name;

    @Schema(description = "{projectDto.description.schema}")
    private String description;

    @Schema(description = "{projectDto.teamName.schema}")
    private String teamName;

    @Schema(description = "{projectDto.status.schema}")
    private String status;

    @Schema(description = "{projectDto.startDate.schema}")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    @Schema(description = "{projectDto.estFinishDate.schema}")
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
