package com.roundsToThree.Structures;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTime {
    public ZonedDateTime value;

    public DateTime(ZonedDateTime dateTime) {
        value = dateTime;
    }

    @Override
    public String toString() {
        return value.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss.SSSSSSa ZZZZ"));
    }
}
