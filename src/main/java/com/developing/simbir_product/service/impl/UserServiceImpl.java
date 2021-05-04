package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.controller.Dto.UserResponseDto;
import com.developing.simbir_product.entity.Role;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.repository.UserRepository;
import com.developing.simbir_product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Transactional
//    @Override
//    public UserResponseDto getById(UUID id) {
//
//        UserEntity userEntity = userRepository.findById(id).orElseThrow(
//                () -> new NotFoundException(String.format("User with ID = '%s' not found", id)));
//
//        UserResponseDto userResponseDto = new UserResponseDto();
//
//        //todo UserResponseDto = mapFrom userEntity !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//
//        return userResponseDto;
//    }

    @Transactional
    @Override
    public boolean addUser(UserRequestDto userRequestDto) {

        Optional<UserEntity> userFromDb = userRepository.findByLogin(userRequestDto.getEmail());

        if (userFromDb.isPresent()) {
            return false; //TODO: ??? или exception?
        }

        UserEntity newUser = new UserEntity();

        //todo userEntity = mapFrom userRequestDto ??????????????????????????????
        //заглушка вместо маппера
        newUser.setLogin(userRequestDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        newUser.setFirstName(userRequestDto.getFullName());
        newUser.setLastName(userRequestDto.getFullName());
        newUser.setRole(Role.ADMIN); // Все админы

        userRepository.save(newUser);

        return true;

//        return new UserResponseDto(); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Transactional
    @Override
    public UserResponseDto editUser(UserRequestDto userRequestDto) {

        UserEntity userEntity = new UserEntity();

        //todo userEntity = mapFrom userRequestDto ????????????????????????

        userRepository.save(userEntity);

        return new UserResponseDto(); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
    }

//    @Transactional
//    @Override
//    public UserResponseDto findByEmail(String email) {
//
//        String login = email; // Т.К. логин и email в нашем случае одно и тоже
//                              // Front знает о email, DB знает о логине
//                              // Service знает что делать с этим
//
//        UserEntity userEntity = userRepository.findByLogin(login).orElseThrow(
//                () -> new NotFoundException(String.format("User with login = '%s' not found", login)));
//
//        UserResponseDto userResponseDto = new UserResponseDto();
//
//        //todo UserResponseDto = mapFrom userEntity !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//
//        return userResponseDto;
//    }
}