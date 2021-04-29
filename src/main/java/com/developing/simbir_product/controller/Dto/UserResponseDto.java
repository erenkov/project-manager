package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Пользователь")
public class UserResponseDto {

    @Schema(description = "E-mail")
    private String email;

    @Schema(description = "Пароль")
    private String password;

    @Schema(description = "Имя и фамилия")
    private String fullName;

    @Schema(description = "Роль")
    private String role;

    @Schema(description = "Команда")
    private String team;

    @Schema(description = "Табельный номер")
    private int userNumber;


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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }
}
