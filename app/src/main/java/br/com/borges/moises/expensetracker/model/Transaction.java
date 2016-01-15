package br.com.borges.moises.expensetracker.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Moisés on 03/12/2015.
 */
public class Transaction {
    private int mId;
    private String mDescription;
    private double mAmount;
    private Account mAccount;
    private Date mDate;
    private TransactionType mType;

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

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public TransactionType getType() {
        return mType;
    }

    public void setType(TransactionType type) {
        mType = type;
    }
}
