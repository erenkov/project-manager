package com.developing.simbir_product.mappers;

import com.developing.simbir_product.entity.TeamEntity;
import com.developing.simbir_product.service.TeamService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class TeamMapper {

    @Autowired
    private TeamService teamService;

    public TeamEntity teamByName(String teamName) {
        return teamService.findByName(teamName);
    }
}
