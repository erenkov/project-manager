package com.developing.simbir_product.mappers;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class DateTimeMapper {

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public OffsetDateTime localToOffsetDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return OffsetDateTime.of(localDateTime, OffsetDateTime.now().getOffset());
    }

    public LocalDateTime offsetToLocalDateTime(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }
        return offsetDateTime.toLocalDateTime();
    }

    public String dateToString(OffsetDateTime dateTime) {
        return dateTime.format(dateFormatter);
    }
}
