package com.developing.simbir_product.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN("A"),
    ROLE_MANAGER("M"),
    ROLE_USER("U");

    private String shortName;

    private Role(String shortName){
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}