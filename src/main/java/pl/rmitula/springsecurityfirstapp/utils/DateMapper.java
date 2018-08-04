package pl.rmitula.springsecurityfirstapp.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Component
public class DateMapper {

    public static LocalDateTime stringToDate(String string) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm", Locale.ENGLISH);
        return LocalDateTime.parse(string, dateTimeFormatter);

    }
}
