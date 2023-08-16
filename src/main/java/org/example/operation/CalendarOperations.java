package org.example.operation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class CalendarOperations {
    public static Calendar stringToCalendar(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(strDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    public static Calendar[] getTargetTimeframeCalendars(String targetTimeframeStr, String tripDateStr) throws ParseException {
        Calendar tripDate = stringToCalendar(tripDateStr);
        Pattern pattern = Pattern.compile(" ");
        String[] regexResult = pattern.split(targetTimeframeStr);

        if (regexResult.length == 2) {
            int amount = Integer.parseInt(regexResult[0]) - 1;
            if(amount < 0) {
                return null;
            }
            Calendar endDate = (Calendar) tripDate.clone();
            endDate.add(Calendar.DATE, amount);
            return new Calendar[]{tripDate, endDate};
        } else if (regexResult.length == 4) {
            Calendar startDate = stringToCalendar(regexResult[1]);
            Calendar endDate = stringToCalendar(regexResult[3]);
            return new Calendar[]{startDate, endDate};
        } else {
            return null;
        }
    }
    public static Set<Calendar> createSetWithFilledDaysInBetween(Calendar startDate, Calendar endDate) {
        Set<Calendar> dates = new HashSet<>();
        Calendar iteratorCal = (Calendar) startDate.clone();

        dates.add(startDate);
        while (iteratorCal.before(endDate)) {
            dates.add((Calendar) iteratorCal.clone());
            iteratorCal.add(Calendar.DATE, 1);
        }
        dates.add(endDate);

        return dates;
    }
    public static Set<Calendar> strDatesToCalendarSet(String[] arr) throws ParseException {
        Set<Calendar> calendars = new HashSet<>();

        if(arr != null) {
            for (String ed : arr) {
                String date = ed.substring(10);
                calendars.add(stringToCalendar(date));
            }
        }

        return calendars;
    }
}
