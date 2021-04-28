package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Schema(description = "Релиз")
public class ReleaseRequestDto {

    @Schema(description = "Название релиза")
    @NotBlank
    @Size(max = 50)
    private String name;

    @Schema(description = "Дата начала релиза")
    private String startDate;

    @Schema(description = "Дата конца релиза")
    private String finishDate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
}
