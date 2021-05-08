package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;

@Schema(description = "Команда")
public class TeamResponseDto {

    @Schema(description = "Название команды")
    private String name;

    @Schema(description = "Описание команды")
    private String description;
}
