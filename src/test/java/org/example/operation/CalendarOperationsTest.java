package org.example.operation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.example.operation.CalendarOperations.stringToCalendar;

public class CalendarOperationsTest {
    @Test
    public void stringToCalendar_incorrectStr_throwsParseException() {
        Assertions.assertThrows(ParseException.class, () -> stringToCalendar("2023-08g-15"));
    }
}
