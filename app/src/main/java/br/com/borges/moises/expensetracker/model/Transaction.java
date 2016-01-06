package br.com.borges.moises.expensetracker.model;

import java.io.Serializable;

/**
 * Created by Moisés on 03/12/2015.
 */
public class Transaction {
    protected int mId;
    protected String mDescription;
    protected double mAmount;
    protected Account mAccount;

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

    public double getAmount() {
        return mAmount;
    }

    public void setAmount(double amount) {
        mAmount = amount;
    }

    public Account getAccount() {
        return mAccount;
    }

    public void setAccount(Account account) {
        mAccount = account;
    }

}
