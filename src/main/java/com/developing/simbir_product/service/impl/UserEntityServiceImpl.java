package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.UserResponseDto;
import com.developing.simbir_product.entity.Role;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.repository.UserRepository;
import com.developing.simbir_product.service.UserEntityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    private UserRepository userRepository;

    @Transactional
    @Override
    public UserResponseDto findByEmail(String login) {
        UserEntity userEntity = userRepository.findByLogin(login).orElseThrow(
                () -> new NotFoundException("User with email = ' ' not found")
        );
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setFullName(userEntity.getFirstName() + " " + userEntity.getLastName());
        userResponseDto.setRole(userEntity.getRole().getShortName());
        userResponseDto.setTeam(userEntity.getTeamId().getName());
        return userResponseDto;
    }


    @Transactional
    @Override
    public boolean addUser(UserEntity user) {
        UserEntity userFromDb = userRepository.findByLogin(user.getLogin()).orElseThrow(
                () -> new NotFoundException("User with email = ' ' not found"));

        if (userFromDb != null) {
            return false;
        }

        user.setRole(Role.USER);
        userRepository.save(user);
        return true;
    }
}

