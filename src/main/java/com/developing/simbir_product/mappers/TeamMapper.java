package com.developing.simbir_product.mappers;

import com.developing.simbir_product.controller.Dto.TeamRequestDto;
import com.developing.simbir_product.controller.Dto.TeamResponseDto;
import com.developing.simbir_product.entity.TeamEntity;
import com.developing.simbir_product.service.TeamService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class TeamMapper {

    @Autowired
    private TeamService teamService;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "users", ignore = true)
    public abstract TeamEntity teamDtoToEntity(TeamRequestDto teamRequestDto);

    public abstract TeamResponseDto teamEntityToDto(TeamEntity teamEntity);

    public TeamEntity teamByName(String teamName) {
        return teamService.findByName(teamName);
    }
}
