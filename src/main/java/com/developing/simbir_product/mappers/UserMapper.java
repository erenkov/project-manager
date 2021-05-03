package com.developing.simbir_product.mappers;


import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.controller.Dto.UserResponseDto;
import com.developing.simbir_product.entity.TeamEntity;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.service.TeamService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class UserMapper {

    @Autowired
    private TeamService teamService;


    @Mapping(target = "fullName", source = ".")
    @Mapping(target = "team", source = "teamId.name")
    @Mapping(target = "email", source = "login")
    public abstract UserResponseDto userEntityToDto(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "login", source = "email")
    @Mapping(target = "firstName", source = "fullName", qualifiedByName = "firstNameByFullName")
    @Mapping(target = "lastName", source = "fullName", qualifiedByName = "lastNameByFullName")
    @Mapping(target = "teamId", source = "team")
    public abstract UserEntity userDtoToEntity(UserRequestDto userRequestDto);


    public String fullNameByUser(UserEntity userEntity) {
        return String.format("%s %s", userEntity.getFirstName(), userEntity.getFirstName());
    }

    @Named("firstNameByFullName")
    public String firstNameByFullName(String fullName) {
        return fullName.split(" ")[0];
    }

    @Named("lastNameByFullName")
    public String lastNameByFullName(String fullName) {
        return fullName.split(" ")[1];
    }

    public TeamEntity teamByName(String teamName) {
        return teamService.findByName(teamName);
    }
}
