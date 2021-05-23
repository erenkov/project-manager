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
                return Role.ROLE_ADMIN;

            case "M":
                return Role.ROLE_MANAGER;

            case "U":
                return Role.ROLE_USER;

            default:
                throw new IllegalArgumentException(String.format("ShortName [%s] not supported.", shortName));
        }
    }
}
