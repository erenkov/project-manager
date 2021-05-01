package com.developing.simbir_product.service;

import com.developing.simbir_product.controller.Dto.UserResponseDto;
import com.developing.simbir_product.entity.UserEntity;

public interface UserEntityService  {
    UserResponseDto findByEmail(String email);

    boolean addUser(UserEntity user);
}
