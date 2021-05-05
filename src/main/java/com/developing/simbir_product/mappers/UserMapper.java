package com.developing.simbir_product.mappers;


import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.controller.Dto.UserResponseDto;
import com.developing.simbir_product.entity.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;


@Mapper(uses = TeamMapper.class, componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {

    @Mapping(target = "team", source = "teamId.name")
    @Mapping(target = "email", source = "login")
    UserResponseDto userEntityToDto(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "login", source = "email")
    @Mapping(target = "teamId", source = "team")
    UserEntity userDtoToEntity(UserRequestDto userRequestDto);
}
