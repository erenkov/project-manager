package com.developing.simbir_product.mappers;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import com.developing.simbir_product.controller.Dto.ReleaseResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.service.ProjectService;
import com.developing.simbir_product.utils.Converter;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


@Mapper(uses = DateTimeMapper.class, imports = {UUID.class, Converter.class}, componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ReleaseMapper {

    @Autowired
    ProjectService projectService;

    @Mapping(target = "id", expression = "java(releaseEntity.getId().toString())")
    @Mapping(target = "projectName", source = "projectId.name")
    public abstract ReleaseResponseDto releaseEntityToDto(ReleaseEntity releaseEntity);

    @Mapping(target = "id", expression = "java(Converter.getUuidFromString(releaseRequestDto.getId()))")
    @Mapping(target = "projectId", source = "projectName")
    public abstract ReleaseEntity releaseDtoToEntity(ReleaseRequestDto releaseRequestDto);

    public ProjectEntity projectByName(String projectName) {
        return projectService.getProjectEntity(projectName);
    }
}
