package sample.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final String LOCAL_DATE_TIME_FORMAT = "dd/MM/yyyy";

    public static String format(String date) {
        try {
            Date result = parseDate(date);
            SimpleDateFormat formatter = new SimpleDateFormat(LOCAL_DATE_TIME_FORMAT);
            return formatter.format(result);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static Date parseDate(String date) throws ParseException {
        java.text.DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.ENGLISH);
        return format.parse(date);
    }
}
