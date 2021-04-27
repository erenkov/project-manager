package com.developing.simbir_product.controller.Dto;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Логин")
public class LoginDto {

    @Schema(description = "E-mail")
    private String email;

    @Schema(description = "Пароль")
    private String password;


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
}
