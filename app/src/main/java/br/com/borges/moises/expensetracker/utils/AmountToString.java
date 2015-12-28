package br.com.borges.moises.expensetracker.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.IllegalFormatException;

/**
 * Created by moise on 28/12/2015.
 */
public class AmountToString {
    public static String convert(double amount) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        return numberFormat.format(amount);
    }

    public static double convert(String str) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        try {
            return numberFormat.parse(str).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Numero " + str + " com formato invalido");
        }
    }
}
