package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.controller.Dto.UserResponseDto;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.mappers.UserMapper;
import com.developing.simbir_product.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void when_addUser_it_should_return_true() {
        UserRequestDto userRequestDto = new UserRequestDto();

        Assertions.assertThat(userService.addUser(userRequestDto)).isTrue();

        Mockito.verify(passwordEncoder, Mockito.times(1)).encode(userRequestDto.getPassword());
        Mockito.verify(userRepository, Mockito.times(1)).save(userMapper.userDtoToEntity(userRequestDto));

    }

    @Test
    public void addUser_exists_should_return_false() {
        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setEmail("test@mail.ru");

        Mockito.doReturn(Optional.of(new UserEntity()))
                .when(userRepository)
                .findByLogin("test@mail.ru");

        Assertions.assertThat(userService.addUser(userRequestDto)).isFalse();

        Mockito.verifyNoInteractions(passwordEncoder);
        Mockito.verify(userRepository, Mockito.times(1)).findByLogin(ArgumentMatchers.any(String.class));
    }

    @Test
    public void find_user_should_return_correct(){
        UUID uuid = UUID.randomUUID();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(uuid);
        userEntity.setLogin("test@mail.ru");
        UserResponseDto expectedUserResponseDto = new UserResponseDto();
        expectedUserResponseDto.setEmail("test@mail.ru");

        Mockito.doReturn(Optional.of(userEntity))
                .when(userRepository)
                .findByLogin(ArgumentMatchers.any(String.class));

        Mockito.doReturn(expectedUserResponseDto)
                .when(userMapper)
                .userEntityToDto(userEntity);

        UserResponseDto actualUserResponseDto = userService.findByEmail("test@mail.ru");

        Assertions.assertThat(actualUserResponseDto.getEmail()).isEqualTo(expectedUserResponseDto.getEmail());

    }
}
