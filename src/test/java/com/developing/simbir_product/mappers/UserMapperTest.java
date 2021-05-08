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
        userRequestDto.setRole("ROLE_USER");
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
        assertEquals(userEntity.getUserNumber(), userDtoTest.getUserNumber());
        assertEquals(userEntity.getLogin(), userDtoTest.getEmail());
        assertEquals(userEntity.getFirstName(), userDtoTest.getFirstName());
        assertEquals(userEntity.getLastName(), userDtoTest.getLastName());
        assertEquals(userEntity.getTeamId().getName(), userDtoTest.getTeam());
        assertEquals(userEntity.getPassword(), userDtoTest.getPassword());
        assertEquals(userEntity.getRole().name(), userDtoTest.getRole());
        userEntity.setTeamId(null);
        userDtoTest = userMapper.userEntityToDto(userEntity);
        assertNull(userDtoTest.getTeam());
    }

    @Test
    void userDtoToEntity() {
        UserEntity userEntityTest = userMapper.userDtoToEntity(userRequestDto);
        assertEquals(userRequestDto.getUserNumber(), userEntityTest.getUserNumber());
        assertEquals(userRequestDto.getFirstName(), userEntityTest.getFirstName());
        assertEquals(userRequestDto.getLastName(), userEntityTest.getLastName());
        assertEquals(userRequestDto.getPassword(), userEntityTest.getPassword());
        assertEquals(userRequestDto.getRole(), userEntityTest.getRole().name());
        assertEquals(userRequestDto.getEmail(), userEntityTest.getLogin());
        assertEquals(userRequestDto.getTeam(), userEntityTest.getTeamId().getName());
    }
}