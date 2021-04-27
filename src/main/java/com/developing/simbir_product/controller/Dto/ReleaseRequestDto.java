package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;


@Schema(description = "Релиз")
public class ReleaseRequestDto {

    @Schema(description = "Дата начала релиза")
    private LocalDateTime startDate;

    @Schema(description = "Дата конца релиза")
    private LocalDateTime finishDate;


    public ReleaseRequestDto(LocalDateTime startDate, LocalDateTime finishDate) {
        this.startDate = startDate;
        this.finishDate = finishDate;
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
