package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.controller.Dto.UserResponseDto;
import com.developing.simbir_product.controller.RegistrationController;
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
            return false;
        }

        userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userRepository.save(userMapper.userDtoToEntity(userRequestDto));
        logger.trace("{} has been created", userRequestDto.getEmail());
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
        userRepository.deleteById(id); //todo Подумать : ЧТО ЛУЧШЕ ВОЗВРАЩАТЬ?
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

    public String getUserNameAndNumber(TaskEntity taskEntity) {
        UserEntity assignee = null;
        try {
            assignee = userTaskHistoryService.getCurrentUserByTask(taskEntity);
        } catch (NotFoundException e) {
            return "";
        }
        return String.format("%s %s %s", assignee.getFirstName(), assignee.getLastName(), assignee.getUserNumber());
    }

    @Override
    public List<String> getListOfAllRoles() {
        return Arrays.stream(Role.values()).map(Role::toString).collect(Collectors.toList());
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
