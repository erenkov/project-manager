package com.developing.simbir_product.exception;

import com.developing.simbir_product.controller.Dto.TeamRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TeamAlreadyExistException extends RuntimeException {

    private final Logger logger = LoggerFactory.getLogger(TeamAlreadyExistException.class);
    private final TeamRequestDto team;

    public TeamAlreadyExistException(String message, TeamRequestDto team) {
        super(message);
        this.team = team;
        logger.info(message);
    }

    public TeamRequestDto getTeam() {
        return team;
    }
}
