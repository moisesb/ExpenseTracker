package br.com.borges.moises.expensetracker.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
