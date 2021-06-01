package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "{teamDto.schema}")
public class TeamResponseDto {

    @Schema(description = "{teamDto.name.schema}")
    private String name;

    @Schema(description = "{teamDto.description.schema}")
    private String description;

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
