package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.controller.Dto.UserResponseDto;
import com.developing.simbir_product.entity.Role;
import com.developing.simbir_product.entity.TaskEntity;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.exception.NotFoundException;
import com.developing.simbir_product.mappers.UserMapper;
import com.developing.simbir_product.repository.UserRepository;
import com.developing.simbir_product.service.UserService;
import com.developing.simbir_product.service.UserTaskHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTaskHistoryService userTaskHistoryService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public boolean addUser(UserRequestDto userRequestDto) {
        UserEntity userEntity = userMapper.userDtoToEntity(userRequestDto);
        Optional<UserEntity> userFromDb = userRepository.findByLogin(userEntity.getLogin());

        if (userFromDb.isPresent()) { // Если пользователь уже есть в БД то не выполняем
            return false;             // добавление пользователя
        }

        userEntity.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userRepository.save(userEntity);
        return true;
    }

    @Override
    public UserEntity addUserEntity(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Transactional
    @Override
    public UserResponseDto editUser(UserRequestDto userRequestDto) {
        UserEntity userEntity = userMapper.userDtoToEntity(userRequestDto);

        UserEntity tempUserFromDB = findEntityByEmail(userEntity.getLogin());
        userEntity.setId(tempUserFromDB.getId());
        userEntity.setPassword(tempUserFromDB.getPassword());
        userEntity.setUserNumber(tempUserFromDB.getUserNumber());
        userEntity = userRepository.save(userEntity);
        logger.trace(userEntity.getLogin() + " has been edited");
        return userMapper.userEntityToDto(userEntity);
    }

    @Transactional
    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UserResponseDto findByEmail(String email) {

        String login = email; // Т.К. логин и email в нашем случае одно и тоже
                              // Front знает о email, DB знает о логине
                              // Service знает что делать с этим

        UserEntity userEntity = userRepository.findByLogin(login).orElseThrow(
                () -> new NotFoundException(String.format("User with login = '%s' not found", login)));

        return userMapper.userEntityToDto(userEntity);
    }

    @Transactional
    @Override
    public UserEntity findUserEntity(String login) {
        return userRepository.findByLogin(login).orElseThrow(
                () -> new NotFoundException(String.format("User with login = '%s' not found", login)));
    }

    @Override
    public String getUserNameAndNumber(TaskEntity taskEntity) {
        UserEntity assignee = null;
        try {
            assignee = userTaskHistoryService.getCurrentUserByTask(taskEntity);
        } catch (NotFoundException e) {
            return "";
        }
        return String.format("%s %s %s", assignee.getFirstName(), assignee.getLastName(), assignee.getUserNumber());
    }

    @Transactional
    @Override
    public List<String> getListOfAllRoles() {
        return Arrays.stream(Role.values()).map(Role::toString).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<UserResponseDto> getListOfAllUsers() {
        return userRepository.findAll().stream().map(userMapper::userEntityToDto).collect(Collectors.toList());
    }

            //todo фильтрация списка юзеров по роли и выводить, как вариант, только роль - ROLE_USER
    @Transactional
    @Override
    public UserEntity findByUserNumber(String userNumber) {
        return userRepository.findByUserNumber(Integer.parseInt(userNumber)).orElseThrow(
                () -> new NotFoundException(String.format("User with number = '%s' not found", userNumber))
        );
    }


    private UserEntity findEntityByEmail(String email) {

        String login = email;   // Т.К. логин и email в нашем случае одно и тоже
        // Front знает о email, DB знает о логине
        // Service знает что делать с этим

        UserEntity userEntity = userRepository.findByLogin(login).orElseThrow(
                () -> new NotFoundException(String.format("User with login = '%s' not found", login)));

        return userEntity;
    }
}
