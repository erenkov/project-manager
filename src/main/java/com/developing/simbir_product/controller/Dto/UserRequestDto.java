package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Schema(description = "Пользователь")
public class UserRequestDto {

    @Schema(description = "E-mail")
    @Email(regexp = "[-a-z0-9!#$%&'*+/=?^_`{|}~]+" +
            "(?:\\.[-a-z0-9!#$%&'*+/=?^_`{|}~]+)*" +
            "@(?:[a-z0-9]([-a-z0-9]{0,61}[a-z0-9])?\\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|" +
            "info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])")
    @NotNull
    @Size(min = 3, max = 320)
    private String email;

    @Schema(description = "Пароль")
    @NotNull
    @Size(min = 8, max = 50)
    private String password;

    @Schema(description = "Имя и фамилия")
    @Size(max = 100)
    private String fullName;

    @Schema(description = "Роль")
    @Size(max = 50)
    private String role;

    @Schema(description = "Команда")
    @Size(max = 50)
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
