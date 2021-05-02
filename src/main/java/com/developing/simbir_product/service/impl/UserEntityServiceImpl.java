package com.developing.simbir_product.service.impl;


import com.developing.simbir_product.dto.UserDto;
import com.developing.simbir_product.dto.UserResponseDto;
import com.developing.simbir_product.entity.Role;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.repository.UserRepository;
import com.developing.simbir_product.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public boolean addUser(UserDto userDto) {
        Optional<UserEntity> userFromDb = userRepository.findByLogin(userDto.getUsername());

        if (userFromDb.isPresent()) {
            return false;
        }

        UserEntity newUser = new UserEntity();
        //TODO: Proper fields then front is done
//        newUser.setLogin(userDto.getUsername());
//        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        newUser.setFirstName("");
//        newUser.setLastName("");
//        newUser.setRole(Collections.singleton(Role.USER));
//        newUser.setUserNumber(1);
        userRepository.save(newUser);
        return true;
    }
}

