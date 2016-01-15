package br.com.borges.moises.expensetracker.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by moise on 11/01/2016.
 */
public enum TransactionType {
    INCOME(2), EXPENSE(1), TRANSFER_IN(3), TRANSFER_OUT(4);

    private final int value;
    private static Map<Integer,TransactionType> sMap;

    TransactionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TransactionType fromId(int id) {
        if (sMap == null) {
            sMap = new HashMap<>();
            for (TransactionType transactionType: TransactionType.values()) {
                sMap.put(transactionType.getValue(),transactionType);
            }
        }

        return sMap.get(id);
    }



}
