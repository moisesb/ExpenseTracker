package br.com.borges.moises.expensetracker.model;

/**
 * Created by Moisés on 03/12/2015.
 */
public class Account {
    private int mId;
    private String mTitle;
    private double mOpeningBalance;
    private int mType;

    public Account() {
    }

    public Account(String title, double openingBalance, int type) {
        mTitle = title;
        mOpeningBalance = openingBalance;
        mType = type;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public double getOpeningBalance() {
        return mOpeningBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        mOpeningBalance = openingBalance;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }
}
