package br.com.borges.moises.expensetracker.model;

/**
 * Created by Moisés on 03/12/2015.
 */
public class Expense extends Transaction{

    public Expense() {

    }

    @Override
    public void execute() {

    }

    @Override
    public void rollback() {

    }

    @Override
    public boolean isValid() {
        return mAmount > 0 && mDescription != null && !mDescription.isEmpty()? true: false;
    }
}
