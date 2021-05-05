package com.developing.simbir_product.mappers;

import com.developing.simbir_product.controller.Dto.UserRequestDto;
import com.developing.simbir_product.controller.Dto.UserResponseDto;
import com.developing.simbir_product.entity.Role;
import com.developing.simbir_product.entity.TeamEntity;
import com.developing.simbir_product.entity.UserEntity;
import com.developing.simbir_product.service.TeamService;
import com.developing.simbir_product.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class UserMapperTest {

    UserRequestDto userRequestDto;
    TeamEntity teamEntity;
    UserEntity userEntity;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;


    @BeforeEach
    void before() {
        teamEntity = new TeamEntity("team name", "description");
        teamEntity = teamService.addTeam(teamEntity);

        userEntity = new UserEntity("email",
                "password",
                "firstname",
                "lastname",
                Role.ROLE_USER);
        userEntity.setUserNumber(100);
        userEntity.setTeamId(teamEntity);
        userEntity = userService.addUserEntity(userEntity);

        userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("email");
        userRequestDto.setFirstName("FirstName");
        userRequestDto.setLastName("LastName");
        userRequestDto.setPassword("password");
        userRequestDto.setTeam("team name");
        userRequestDto.setRole("USER");
        userRequestDto.setUserNumber(100);
    }

    @AfterEach
    void after() {
        userService.deleteById(userEntity.getId());
        teamService.deleteById(teamEntity.getId());
    }


    @Test
    void userEntityToDto() {
        UserResponseDto userDtoTest = userMapper.userEntityToDto(userEntity);
        assertEquals(userDtoTest.getUserNumber(), userEntity.getUserNumber());
        assertEquals(userDtoTest.getEmail(), userEntity.getLogin());
        assertEquals(userDtoTest.getFullName(), String.format("%s %s",
                userEntity.getFirstName(),
                userEntity.getLastName()));
        assertEquals(userDtoTest.getTeam(), userEntity.getTeamId().getName());
        assertEquals(userDtoTest.getPassword(), userEntity.getPassword());
        assertEquals(userDtoTest.getRole(), userEntity.getRole().name());
        userEntity.setTeamId(null);
        userDtoTest = userMapper.userEntityToDto(userEntity);
        assertNull(userDtoTest.getTeam());
    }

    @Test
    void userDtoToEntity() {
        UserEntity userEntityTest = userMapper.userDtoToEntity(userRequestDto);
        assertEquals(userEntityTest.getUserNumber(), userRequestDto.getUserNumber());
        assertEquals(userEntityTest.getFirstName(), userRequestDto.getFirstName());
        assertEquals(userEntityTest.getLastName(), userRequestDto.getLastName());
        assertEquals(userEntityTest.getPassword(), userRequestDto.getPassword());
        assertEquals(userEntityTest.getRole().name(), userRequestDto.getRole());
        assertEquals(userEntityTest.getLogin(), userRequestDto.getEmail());
        assertEquals(userEntityTest.getTeamId().getName(), userRequestDto.getTeam());
    }
}