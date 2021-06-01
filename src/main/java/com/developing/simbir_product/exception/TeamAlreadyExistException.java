package com.developing.simbir_product.exception;

import com.developing.simbir_product.controller.Dto.TeamRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;


public class TeamAlreadyExistException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(TeamAlreadyExistException.class);
    private final MessageSource messageSource;
    private final TeamRequestDto team;

    public TeamAlreadyExistException(String message, TeamRequestDto team, MessageSource messageSource) {
        super(message);
        this.team = team;
        this.messageSource = messageSource;
        logger.info(messageSource.getMessage(message, new String[]{team.getName()}, Locale.getDefault()));
    }

    public TeamRequestDto getTeam() {
        return team;
    }

    @Override
    public String getLocalizedMessage() {
        return messageSource.getMessage(super.getMessage(), new String[]{team.getName()},
                LocaleContextHolder.getLocale());
    }
}
