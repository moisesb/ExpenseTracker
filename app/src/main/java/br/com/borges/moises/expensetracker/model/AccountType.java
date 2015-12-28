package br.com.borges.moises.expensetracker.model;

/**
 * Created by moise on 21/12/2015.
 */
public class AccountType {

    private int mId;
    private String mDescription;

    public AccountType() {
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
}
