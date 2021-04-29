package com.developing.simbir_product.converter;

import com.developing.simbir_product.entity.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        return role.getShortName();
    }

    @Override
    public Role convertToEntityAttribute(String shortName) {
        switch (shortName) {
            case "A":
                return Role.ADMIN;

            case "M":
                return Role.MANAGER;

            case "U":
                return Role.USER;

            default:
                throw new IllegalArgumentException("ShortName [" + shortName
                        + "] not supported.");
        }
    }
}
