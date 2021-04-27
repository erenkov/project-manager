package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;


@Schema(description = "Релиз")
public class ReleaseResponseDto {

    @Schema(description = "ID релиза")
    private UUID id;

    @Schema(description = "Дата начала релиза")
    private LocalDateTime startDate;

    @Schema(description = "Дата конца релиза")
    private LocalDateTime finishDate;


    public ReleaseResponseDto(UUID id, LocalDateTime startDate, LocalDateTime finishDate) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
