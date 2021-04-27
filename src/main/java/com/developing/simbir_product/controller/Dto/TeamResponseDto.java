package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;


@Schema(description = "Команда")
public class TeamResponseDto {

    @Schema(description = "ID команды")
    private UUID id;

    @Schema(description = "Название команды")
    private String name;

    @Schema(description = "Описание")
    private String description;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
}
