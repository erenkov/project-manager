package com.developing.simbir_product.service.impl;


import com.developing.simbir_product.dto.UserDto;
import com.developing.simbir_product.dto.UserResponseDto;
import com.developing.simbir_product.entity.Role;
import com.developing.simbir_product.entity.TeamEntity;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.repository.UserRepository;
import com.developing.simbir_product.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserResponseDto findByEmail(String login) {
        UserEntity userEntity = userRepository.findByLogin(login).orElseThrow(
                () -> new NotFoundException("User with email = ' ' not found")
        );
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setFullName(userEntity.getFirstName() + " " + userEntity.getLastName());
//        userResponseDto.setRole(userEntity.getRole());
//        userResponseDto.setTeam(userEntity.getTeamId().getName());
        return userResponseDto;
    }


    @Transactional
    @Override
    public boolean addUser(UserDto userDto) {
        Optional<UserEntity> userFromDb = userRepository.findByLogin(userDto.getUsername());
        UUID uuid = UUID.randomUUID();
        UUID uuid1 = UUID.randomUUID();
        if (userFromDb.isPresent()) {
            return false;
        }
        UserEntity newUser = new UserEntity();
        newUser.setId(uuid);
        newUser.setLogin(userDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setFirstName("Ilnaz");
        newUser.setLastName("Gilm");
        newUser.setRole(Collections.singleton(Role.USER));
        userRepository.save(newUser);
        return true;
    }
}

