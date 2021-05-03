package com.developing.simbir_product.mappers;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;


@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public abstract class DateTimeMapper {

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public OffsetDateTime stringToDate(String stringDate) { // TODO: Получать ZoneOffset клиента
        return OffsetDateTime.of(LocalDate.parse(stringDate, dateFormatter), LocalTime.MIDNIGHT, ZoneOffset.UTC);
    }

    public String dateToString(OffsetDateTime dateTime) {
        return dateTime.format(dateFormatter);
    }
}
