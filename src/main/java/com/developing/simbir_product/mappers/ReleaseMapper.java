package com.developing.simbir_product.mappers;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import com.developing.simbir_product.controller.Dto.ReleaseResponseDto;
import com.developing.simbir_product.entity.ReleaseEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;


@Mapper(uses = DateTimeMapper.class, componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ReleaseMapper {

    ReleaseResponseDto releaseEntityToDto(ReleaseEntity releaseEntity);

    @Mapping(target = "id", ignore = true)
    ReleaseEntity releaseDtoToEntity(ReleaseRequestDto releaseRequestDto);
}
