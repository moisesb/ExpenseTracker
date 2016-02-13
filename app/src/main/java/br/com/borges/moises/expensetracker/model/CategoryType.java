package br.com.borges.moises.expensetracker.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by moise on 30/12/2015.
 */
public enum CategoryType {
    EXPENSE(1),INCOME(2);

    private final int value;
    private static Map<Integer,CategoryType> sMap;

    CategoryType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CategoryType fromId(int id) {
        if (sMap == null) {
            sMap = new HashMap<>();
            for (CategoryType categoryType : CategoryType.values()) {
                sMap.put(categoryType.getValue(), categoryType);
            }
        }

        return sMap.get(id);
    }
}
