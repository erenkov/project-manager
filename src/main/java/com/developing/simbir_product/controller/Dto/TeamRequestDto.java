package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Schema(description = "{teamDto.schema}")
public class TeamRequestDto {

    @Schema(description = "{teamDto.name.schema}")
    @NotBlank(message = "{teamDto.name.notBlank}")
    @Pattern(regexp = "(?U)[\\w&&[^_]]+[-\\w$.+!*'() ]*", message = "{teamDto.name.pattern}")
    @Size(max = 50, message = "{teamDto.name.size}")
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
