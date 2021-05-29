package com.developing.simbir_product.controller.Dto;

import com.developing.simbir_product.validators.ReleaseWithDates;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Schema(description = "Релиз")
@ReleaseWithDates
public class ReleaseRequestDto {

    @Schema(description = "Идентификатор релиза")
    private String id;

    @Schema(description = "Название релиза")
    @NotBlank(message = "Name must not be empty")
    @Size(max = 50, message = "Name must not be greater than 50 characters")
    private String name;

    @Schema(description = "Дата начала релиза")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Start date must not be empty")
    private LocalDateTime startDate;

    @Schema(description = "Дата конца релиза")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Finish date must not be empty")
    private LocalDateTime finishDate;

    @Schema(description = "Название проекта")
    @Size(max = 50, message = "Project name must not be greater than 50 characters")
    private String projectName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
