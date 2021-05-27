package com.developing.simbir_product.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;


public class Converter {

    public static UUID getUuidFromString(String stringUuid) {
        if (StringUtils.isEmpty(stringUuid) || !isValidUuid(stringUuid)) {
            return null;
        }
        return UUID.fromString(stringUuid);
    }

    public static boolean isValidUuid(String stringUuid) {
        try {
            UUID.fromString(stringUuid);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
