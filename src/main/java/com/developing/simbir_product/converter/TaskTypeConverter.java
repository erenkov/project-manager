package com.developing.simbir_product.converter;

import com.developing.simbir_product.entity.TaskType;

import javax.persistence.AttributeConverter;

public class TaskTypeConverter implements AttributeConverter<TaskType, String> {

    @Override
    public String convertToDatabaseColumn(TaskType taskType) {
        return taskType.getShortName();
    }

    @Override
    public TaskType convertToEntityAttribute(String shortName) {
        switch (shortName) {
            case "B":
                return TaskType.BUGFIX;

            case "F":
                return TaskType.FEATURE;

            case "H":
                return TaskType.HOTFIX;

            default:
                throw new IllegalArgumentException("ShortName [" + shortName
                        + "] not supported.");
        }
    }
}
