package com.developing.simbir_product.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;


public class Converter {

    private final static Logger logger = LoggerFactory.getLogger(Converter.class);

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
            logger.debug("Wrong UUID format: {}", stringUuid);
            return false;
        }
    }

    public static Integer getUserNumberFromAssignee(String assignee) {
        if (StringUtils.isBlank(assignee)) {
            return 0;
        }
        String[] assigneeSplit = assignee.split(" ");
        if (assigneeSplit.length < 3) {
            return 0;
        }
        int userNumber;
        try {
            userNumber = Integer.parseInt(assigneeSplit[assigneeSplit.length - 1]);
        } catch (NumberFormatException e) {
            logger.debug("Wrong user number format for assignee {}", assignee);
            return 0;
        }
        return userNumber;
    }
}
