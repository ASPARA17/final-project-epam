package com.epam.jwd.service.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class DateUtil {
    private DateUtil() {
    }

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public static Date takeCurrentDateFormat() {
        Date currentDate = new Date();
        String currentDateFormat = DATE_FORMAT.format(currentDate);
        Optional<Date> currentDateOptional = parseStringToDateFormat(currentDateFormat);
        return currentDateOptional.orElse(currentDate);
    }

    public static Optional<Date> parseStringToDateFormat(String dateString) {
        Optional<Date> dateFormat;
        try {
            Date date = DATE_FORMAT.parse(dateString);
            dateFormat = Optional.of(date);
        } catch (ParseException exp) {
            dateFormat = Optional.empty();
        }
        return dateFormat;
    }

    public static String parseDateToStringFormat(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}
