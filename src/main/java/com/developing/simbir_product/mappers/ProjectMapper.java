package com.developing.simbir_product.mappers;

import com.developing.simbir_product.controller.Dto.ProjectRequestDto;
import com.developing.simbir_product.controller.Dto.ProjectResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;


@Mapper(uses = {DateTimeMapper.class, TeamMapper.class}, componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ProjectMapper {

    @Mapping(target = "teamName", source = "teamId.name")
    @Mapping(target = "status", source = "projectStatus")
    public abstract ProjectResponseDto projectEntityToDto(ProjectEntity projectEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "finishDate", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "teamId", source = "teamName")
    @Mapping(target = "projectStatus", source = "status")
    public abstract ProjectEntity projectDtoToEntity(ProjectRequestDto projectRequestDto);
}
