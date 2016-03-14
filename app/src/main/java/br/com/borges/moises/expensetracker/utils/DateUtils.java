package br.com.borges.moises.expensetracker.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by moise on 13/02/2016.
 */
public final class DateUtils {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public String getDateStr(Date date) {
        return dateFormat.format(date);
    }

    public Date getDate(String dateStr) {
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static int getCurrentYear() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.MONTH);
    }
}
