package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Команда")
public class TeamRequestDto {

    @Schema(description = "Название команды")
    private String name;

    @Schema(description = "Описание команды")
    private String description;
}
