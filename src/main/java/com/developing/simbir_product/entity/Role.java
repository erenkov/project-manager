package com.developing.simbir_product.entity;

public enum Role {
    ADMIN("A"),
    MANAGER("M"),
    USER("U");

    private String shortName;

    private Role(String shortName){
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}
