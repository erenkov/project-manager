package com.developing.simbir_product.entity;

public enum ProjectStatus {
    BACKLOG("B"),
    IN_PROGRESS("IN_P"),
    DONE("D"),
    CLOSED("C");

    private String shortName;

    private ProjectStatus(String shortName){
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}
