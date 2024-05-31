package com.example.todolist.common.mapper;

import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author jinwook.kim
 * @since 2024. 5. 31.
 */
public class BaseMapper {
    @Named("convertDateToTime")
    protected Long convertDateToTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }
}
