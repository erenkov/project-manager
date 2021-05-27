package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Schema(description = "Пользователь")
public class UserRequestDto {

    @Schema(description = "E-mail")
    @Email(regexp = "[-a-zA-Z0-9!#$%&'*+/=?^_`{|}~]+" +
            "(?:\\.[-a-zA-Z0-9!#$%&'*+/=?^_`{|}~]+)*" +
            "@(?:[a-zA-Z0-9]([-a-zA-Z0-9]{0,61}[a-zA-Z0-9])?\\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|" +
            "info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])",
            message = "E-mail must be in valid format")
    @NotBlank(message = "E-mail must not be empty")
    @Size(min = 3, max = 320)
    private String email;

    @Schema(description = "Пароль")
    @NotBlank(message = "Password must not be empty")
    @Size(min = 8, max = 50, message = "Password must be at least 8 and not greater than 50 characters")
    private String password;

    @Schema(description = "Имя")
    @NotBlank(message = "First name must not be empty")
    @Size(max = 50, message = "First name must not be greater than 50 characters")
    private String firstName;

    @Schema(description = "Фамилия")
    @NotBlank(message = "Last name must not be empty")
    @Size(max = 50, message = "Last name must not be greater than 50 characters")
    private String lastName;

    @Schema(description = "Роль")
    @NotBlank(message = "Role name must not be empty")
    @Size(max = 50, message = "Role name must not be greater than 50 characters")
    private String role;

    @Schema(description = "Команда")
    @Size(max = 50, message = "Team name must not be greater than 50 characters")
    private String team;

    @Schema(description = "Табельный номер")
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
