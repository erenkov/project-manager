package com.developing.simbir_product.mappers;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import com.developing.simbir_product.controller.Dto.ReleaseResponseDto;
import com.developing.simbir_product.entity.ProjectEntity;
import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.service.ProjectService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(uses = DateTimeMapper.class, componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class ReleaseMapper {

    @Autowired
    ProjectService projectService;

    @Mapping(target = "projectName", source = "projectId.name")
    public abstract ReleaseResponseDto releaseEntityToDto(ReleaseEntity releaseEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "projectId", source = "projectName")
    public abstract ReleaseEntity releaseDtoToEntity(ReleaseRequestDto releaseRequestDto);

    public ProjectEntity projectByName(String projectName) {
        return projectService.getProjectEntity(projectName);
    }
}
