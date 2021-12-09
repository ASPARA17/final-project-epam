package com.epam.jwd.service.validator;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator {
    private static final String DATE_REGEX = "^\\d{2}\\-\\d{2}\\-\\d{4}$";
    private static final String DATE_REGEX_DELIMITER = "-";
    private static final int DATE_YEAR_INDEX = 2;
    private static final int DATE_MONTH_INDEX = 1;
    private static final int DATE_DAY_INDEX = 0;
    private static final int LOW_BORDER_MONTH_AND_DAY = 1;
    private static final int LOW_BORDER_YEAR = 2015;
    private static final int HIGH_BORDER_YEAR = 2050;
    private static final int HIGH_BORDER_MONTH = 12;
    private static final int HIGH_BORDER_DAY = 31;

    public boolean checkDate(String dateOfIssue, String returnDate) {
        boolean isCorrect = false;
        Date currentDay = DateUtil.takeCurrentDateFormat();
        if (isEmptyOrNull(dateOfIssue)
                && isEmptyOrNull(returnDate)
                && isStringMatches(dateOfIssue, DATE_REGEX)
                && isStringMatches(returnDate, DATE_REGEX)) {
            if (validateDateValues(dateOfIssue)
                    && validateDateValues(returnDate)) {
                Optional<Date> dateOfIssueFormatOptional = DateUtil.parseStringToDateFormat(dateOfIssue);
                Optional<Date> returnDateFormatOptional = DateUtil.parseStringToDateFormat(returnDate);
                if (dateOfIssueFormatOptional.isPresent()
                        && returnDateFormatOptional.isPresent()) {
                    Date dateOfIssueFormat = dateOfIssueFormatOptional.get();
                    Date returnDateFormat = returnDateFormatOptional.get();
                    if (compareDates(currentDay, dateOfIssueFormat, returnDateFormat)) {
                        isCorrect = true;
                    }
                }
            }
        }
        return isCorrect;
    }

    private boolean isEmptyOrNullAndStringMatches(String string, String regex) {
        return isEmptyOrNull(string) && isStringMatches(string, regex);
    }

    private boolean isDayCorrect(int day) {
        return LOW_BORDER_MONTH_AND_DAY <= day && day <= HIGH_BORDER_DAY;
    }

    private boolean isMonthCorrect(int month) {
        return LOW_BORDER_MONTH_AND_DAY <= month && month <= HIGH_BORDER_MONTH;
    }

    private boolean isYearCorrect(int year) {
        return LOW_BORDER_YEAR <= year && year <= HIGH_BORDER_YEAR;
    }

    private boolean validateDateValues(String date) {
        String[] partsDate = date.split(DATE_REGEX_DELIMITER);
        int day = Integer.parseInt(partsDate[DATE_DAY_INDEX]);
        int month = Integer.parseInt(partsDate[DATE_MONTH_INDEX]);
        int year = Integer.parseInt(partsDate[DATE_YEAR_INDEX]);
        return isYearCorrect(year) && isMonthCorrect(month) && isDayCorrect(day);
    }

    private boolean compareDates(Date currentDate, Date dateOfIssue, Date returnDate) {
        boolean isCorrect = false;
        if ((currentDate.before(dateOfIssue)
                || currentDate.equals(dateOfIssue))
                && currentDate.before(returnDate)
                && dateOfIssue.before(returnDate)) {
            isCorrect = true;
        }
        return isCorrect;
    }

    private static boolean isStringMatches(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    private boolean isEmptyOrNull(String string) {
        return string != null && !string.isEmpty();
    }
}
