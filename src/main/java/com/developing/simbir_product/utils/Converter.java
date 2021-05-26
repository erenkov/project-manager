package com.developing.simbir_product.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;


public class Converter {

    public static UUID getUuidFromString(String stringUuid) {
        if (StringUtils.isEmpty(stringUuid)) {
            return null;
        }
        return UUID.fromString(stringUuid);
    }
}
