package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.UserResponseDto;
import com.developing.simbir_product.entity.Role;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.repository.UserRepository;
import com.developing.simbir_product.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.soap.Addressing;

@Service
public class UserEntityServiceImpl implements UserEntityService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserResponseDto findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Project with email = ' ' not found")
        );
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setFullName(userEntity.getFirstName());
        userResponseDto.setRole(userEntity.getRole().getShortName());
        userResponseDto.setTeam(userEntity.getTeamId().getName());
        return userResponseDto;
    }


    @Transactional
    @Override
    public boolean addUser(UserEntity user) {
        UserEntity userFromDb = userRepository.findByLogin(user.getLogin());

        if (userFromDb != null) {
            return false;
        }

        user.setRole(Role.USER);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}

