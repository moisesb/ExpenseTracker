package br.com.borges.moises.expensetracker.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by moise on 11/01/2016.
 */
public enum TransactionCategory {
    INCOME(2), EXPENSE(1), TRANSFER_IN(3), TRANSFER_OUT(4);

    private final int value;
    private static Map<Integer,TransactionCategory> sMap;

    TransactionCategory(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TransactionCategory fromId(int id) {
        if (sMap == null) {
            sMap = new HashMap<>();
            for (TransactionCategory transactionCategory : TransactionCategory.values()) {
                sMap.put(transactionCategory.getValue(), transactionCategory);
            }
        }

        return sMap.get(id);
    }



}
