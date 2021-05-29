package com.developing.simbir_product.converter;

import com.developing.simbir_product.entity.TaskStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = true)
public class TaskStatusConverter implements AttributeConverter<TaskStatus, String> {

    @Override
    public String convertToDatabaseColumn(TaskStatus taskStatus) {
        return taskStatus.getShortName();
    }

    @Override
    public TaskStatus convertToEntityAttribute(String shortName) {
        switch (shortName) {
            case "B":
                return TaskStatus.BACKLOG;

            case "IN_P":
                return TaskStatus.IN_PROGRESS;

            case "D":
                return TaskStatus.DONE;

            default:
                throw new IllegalArgumentException(String.format("ShortName [%s] not supported.", shortName));
        }
    }
}
