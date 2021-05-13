package com.developing.simbir_product.service;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.controller.Dto.UserResponseDto;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.UserEntity;

import java.util.List;
import java.util.UUID;


public interface UserService {

//    UserResponseDto getById(UUID id);
//
    UserResponseDto findByEmail(String email);

    UserEntity findUserEntity(String email);

    boolean addUser(UserRequestDto userRequestDto);

    UserEntity addUserEntity(UserEntity userEntity);

    UserResponseDto editUser(UserRequestDto userRequestDto);

    void deleteById(UUID id);

    String getUserNameAndNumber(TaskEntity taskEntity);

    List<String> getListOfAllRoles();

    List<UserResponseDto> getListOfAllUsers();
}
