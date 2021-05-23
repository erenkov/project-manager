package com.developing.simbir_product.entity;

public enum TaskStatus {
    BACKLOG("B"),
    IN_PROGRESS("IN_P"),
    DONE("D");

    private final String shortName;

    TaskStatus(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}
