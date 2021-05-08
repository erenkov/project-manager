package com.developing.simbir_product.utils;

import java.util.UUID;


public class Converter {

    public static UUID getUuidFromString(String stringUuid) {
        if (stringUuid == null || stringUuid.isEmpty()) {
            return null;
        }
        return UUID.fromString(stringUuid);
    }
}
