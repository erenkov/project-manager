package com.developing.simbir_product.entity;

public enum TaskType {
    FEATURE("F"),
    BUGFIX("B"),
    HOTFIX("H");

    private String shortName;

    private TaskType(String shortName){
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}
