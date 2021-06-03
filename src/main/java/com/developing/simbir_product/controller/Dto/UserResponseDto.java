package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "{user.schema}")
public class UserResponseDto {

    @Schema(description = "{user.email.schema}")
    private String email;

    @Schema(description = "{user.password.schema}")
    private String password;

    @Schema(description = "{user.firstName.schema}")
    private String firstName;

    @Schema(description = "{user.lastName.schema}")
    private String lastName;

    @Schema(description = "{user.role.schema}")
    private String role;

    @Schema(description = "{teamDto.name.schema}")
    private String team;

    @Schema(description = "{user.userNumber.schema}")
    private Integer userNumber;


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

    public Integer getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Integer userNumber) {
        this.userNumber = userNumber;
    }
}
