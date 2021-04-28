package com.developing.simbir_product.converter;

import com.developing.simbir_product.entity.ProjectStatus;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ProjectStatusConverter implements AttributeConverter<ProjectStatus, String> {

    @Override
    public String convertToDatabaseColumn(ProjectStatus projectStatus) {
        return projectStatus.getShortName();
    }

    @Override
    public ProjectStatus convertToEntityAttribute(String shortName) {
        switch (shortName) {
            case "B":
                return ProjectStatus.BACKLOG;

            case "IN_P":
                return ProjectStatus.IN_PROGRESS;

            case "D":
                return ProjectStatus.DONE;

            case "C":
                return ProjectStatus.CLOSED;

            default:
                throw new IllegalArgumentException("ShortName [" + shortName
                        + "] not supported.");
        }
    }
}
