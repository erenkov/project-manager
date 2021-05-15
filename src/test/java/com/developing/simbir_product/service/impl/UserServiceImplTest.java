package com.developing.simbir_product.service.impl;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.controller.Dto.UserResponseDto;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.mappers.UserMapper;
import com.developing.simbir_product.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
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

    @Captor
    private ArgumentCaptor<UserEntity> captor;

    @Test
    @DisplayName("Should add user and return true")
    void when_addUser_it_should_return_true() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setPassword("test");

        UserEntity userEntity = userMapper.userDtoToEntity(userRequestDto);

        boolean isUserAdded = userService.addUser(userRequestDto);
        Assertions.assertThat(isUserAdded).isTrue();

        Mockito.verify(passwordEncoder, Mockito.times(1)).encode("test");
        Mockito.verify(userRepository, Mockito.times(1)).save(userEntity);

    }

    @Test
    @DisplayName("Should return false if user exists")
    public void addUser_exists_should_return_false() {
        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setEmail("test@mail.ru");

        Mockito.doReturn(Optional.of(new UserEntity()))
                .when(userRepository)
                .findByLogin("test@mail.ru");


        boolean isUserAdded = userService.addUser(userRequestDto);
        Assertions.assertThat(isUserAdded).isFalse();

        Mockito.verifyNoInteractions(passwordEncoder);
        Mockito.verify(userRepository, Mockito.times(1)).findByLogin(ArgumentMatchers.any(String.class));

    }

    @Test
    @DisplayName("Should return user")
    public void find_user_should_return_correct(){
        UUID uuid = UUID.randomUUID();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(uuid);
        userEntity.setLogin("test@mail.ru");
        UserResponseDto expectedUserResponseDto = new UserResponseDto();
        expectedUserResponseDto.setEmail("test@mail.ru");

        Mockito.doReturn(Optional.of(userEntity))
                .when(userRepository)
                .findByLogin("test@mail.ru");

        Mockito.doReturn(expectedUserResponseDto)
                .when(userMapper)
                .userEntityToDto(userEntity);

        UserResponseDto actualUserResponseDto = userService.findByEmail("test@mail.ru");

        Assertions.assertThat(actualUserResponseDto.getEmail()).isEqualTo(expectedUserResponseDto.getEmail());

    }
}
