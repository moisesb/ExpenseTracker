package br.com.borges.moises.expensetracker.model;

import java.util.Date;

/**
 * Created by Moisés on 03/12/2015.
 */
public class Transaction {
    private int mId;
    private String mDescription;
    private double mAmount;
    private Account mAccount;
    private Category mCategory;
    private Date mDate;
    private TransactionCategory mType;

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

    public TransactionCategory getType() {
        return mType;
    }

    public void setType(TransactionCategory type) {
        mType = type;
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category category) {
        mCategory = category;
    }
}
