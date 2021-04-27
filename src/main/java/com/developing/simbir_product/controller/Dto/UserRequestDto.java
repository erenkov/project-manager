package com.developing.simbir_product.controller.Dto;

import com.developing.simbir_product.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Пользователь")
public class UserRequestDto {

    @Schema(description = "E-mail")
    private String email;

    @Schema(description = "Пароль")
    private String password;

    @Schema(description = "Имя")
    private String firstName;

    @Schema(description = "Фамилия")
    private String lastName;

    @Schema(description = "Роль")
    private Role role;

    @Schema(description = "Команда")
    private TeamResponseDto teamDto;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public TeamResponseDto getTeamDto() {
        return teamDto;
    }

    public void setTeamDto(TeamResponseDto teamDto) {
        this.teamDto = teamDto;
    }

}
