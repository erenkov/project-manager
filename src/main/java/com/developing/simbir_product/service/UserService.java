package com.developing.simbir_product.service;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.controller.Dto.UserResponseDto;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.UserEntity;

import java.util.UUID;


public interface UserService {

//    UserResponseDto getById(UUID id);
//
    UserResponseDto findByEmail(String email);

    boolean addUser(UserRequestDto userRequestDto);

    UserEntity addUserEntity(UserEntity userEntity);

    UserResponseDto editUser(UserRequestDto userRequestDto);

    void deleteById(UUID id);

    String getUserNameAndNumber(TaskEntity taskEntity);
}
