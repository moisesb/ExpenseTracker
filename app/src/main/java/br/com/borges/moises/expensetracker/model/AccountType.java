package br.com.borges.moises.expensetracker.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by moise on 21/12/2015.
 */
public enum AccountType {
    CHECKING_ACCOUNT(1),
    CASH(2),
    SAVINGS(3),
    INVESTIMENT(4),
    CREDIT_CARD(5);

    private int mId;
    private int mDescription;

    private int mValue;

    private AccountType(int val) {
        mValue = val;
    }

    public int getValue() {
        return mValue;
    }

    private static final Map<Integer, AccountType> mMap = new HashMap<Integer, AccountType>();
    static
    {
        for (AccountType type : AccountType.values())
            mMap.put(type.getValue(), type);
    }

    public static AccountType from(int value) {
        return mMap.get(value);
    }
}
