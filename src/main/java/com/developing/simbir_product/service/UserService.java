package com.developing.simbir_product.service;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.controller.Dto.UserResponseDto;

import java.util.UUID;

public interface UserService {

//    UserResponseDto getById(UUID id);
//
//    UserResponseDto findByEmail(String email);

    boolean addUser(UserRequestDto userRequestDto);

    UserResponseDto editUser(UserRequestDto userRequestDto);

    void deleteById(UUID id);

}
