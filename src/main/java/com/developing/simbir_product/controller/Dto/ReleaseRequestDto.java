package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Schema(description = "Релиз")
public class ReleaseRequestDto {

    @Schema(description = "Название релиза")
    @NotBlank
    @Size(max = 50)
    private String name;

    @Schema(description = "Дата начала релиза")
    private LocalDateTime startDate;

    @Schema(description = "Дата конца релиза")
    private LocalDateTime finishDate;


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
}
