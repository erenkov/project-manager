package com.developing.simbir_product.service;

import com.developing.simbir_product.dto.UserDto;
import com.developing.simbir_product.dto.UserResponseDto;
import com.developing.simbir_product.entity.UserEntity;

public interface UserEntityService  {
    UserResponseDto findByEmail(String email);

    boolean addUser(UserDto userDto);
}
