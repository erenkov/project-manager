package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Schema(description = "Команда")
public class TeamRequestDto {

    @Schema(description = "Название команды")
    @NotBlank
    @Size(max = 50)
    private String name;

    @Schema(description = "Описание команды")
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
