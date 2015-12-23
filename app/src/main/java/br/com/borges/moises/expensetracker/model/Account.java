package br.com.borges.moises.expensetracker.model;

/**
 * Created by Moisés on 03/12/2015.
 */
public class Account {
    private int mId;
    private String mDescription;
    private double mOpeningBalance;
    private AccountType mType;

    public Account() {
    }

    public Account(String description, double openingBalance, AccountType type) {
        mDescription = description;
        mOpeningBalance = openingBalance;
        mType = type;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public double getOpeningBalance() {
        return mOpeningBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        mOpeningBalance = openingBalance;
    }

    public AccountType getType() {
        return mType;
    }

    public void setType(AccountType type) {
        mType = type;
    }
}
