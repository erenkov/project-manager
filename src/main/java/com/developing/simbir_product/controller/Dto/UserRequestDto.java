package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Schema(description = "{user.schema}")
public class UserRequestDto {

    @Schema(description = "{user.email.schema}")
    @Email(regexp = "[-a-zA-Z0-9!#$%&'*+/=?^_`{|}~]+" +
            "(?:\\.[-a-zA-Z0-9!#$%&'*+/=?^_`{|}~]+)*" +
            "@(?:[a-zA-Z0-9]([-a-zA-Z0-9]{0,61}[a-zA-Z0-9])?\\.)*(?:aero|arpa|asia|biz|cat|com|coop|edu|gov|" +
            "info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-z][a-z])",
            message = "{user.email.email}")
    @NotBlank(message = "{user.email.notBlank}")
    @Size(min = 3, max = 320)
    private String email;

    @Schema(description = "{user.password.schema}")
    @NotBlank(message = "{user.password.notBlank}")
    @Size(min = 8, max = 50, message = "{user.password.size}")
    private String password;

    @Schema(description = "{user.firstName.schema}")
    @NotBlank(message = "{user.firstName.notBlank}")
    @Size(max = 50, message = "{user.firstName.size}")
    private String firstName;

    @Schema(description = "{user.lastName.schema}")
    @NotBlank(message = "{user.lastName.notBlank}")
    @Size(max = 50, message = "{user.lastName.size}")
    private String lastName;

    @Schema(description = "{user.role.schema}")
    @NotBlank(message = "{user.role.notBlank}")
    @Size(max = 50, message = "{user.role.size}")
    private String role;

    @Schema(description = "{teamDto.name.schema}")
    @Size(max = 50, message = "{teamDto.name.size}")
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
