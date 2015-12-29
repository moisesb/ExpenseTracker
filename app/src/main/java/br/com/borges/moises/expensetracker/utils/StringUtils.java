package br.com.borges.moises.expensetracker.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.IllegalFormatException;
import java.util.Locale;

/**
 * Created by moise on 28/12/2015.
 */
public class StringUtils {


    public static String convertDoubleToString(double value) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(value);
    }

    public static double convertStringToDouble(String str) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        try {
            return numberFormat.parse(str).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Numero " + str + " com formato invalido");
        }
    }

    public static String convertDoubleToStringWithCurrency(double value) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        return numberFormat.format(value);
    }
}
