package br.com.borges.moises.expensetracker.db.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.borges.moises.expensetracker.db.helpers.ExpenseTrackerBaseHelper;
import br.com.borges.moises.expensetracker.model.Account;

/**
 * Created by Moisés on 15/12/2015.
 */
public class AccountDao {

    private static AccountDao sAccountDao;
    private SQLiteDatabase sDatabase;

    private AccountDao(Context context) {
        sDatabase = ExpenseTrackerBaseHelper.getBaseHelper(context)
                .getWritableDatabase();
    }

    public static AccountDao getAccountDao(Context context) {
        if (sAccountDao == null) {
            sAccountDao = new AccountDao(context);
        }

        return sAccountDao;
    }

    public Account getAccount(int id) {
        return null;
    }

    public List<Account> getAccounts() {
        return null;
    }

    public void addAccount(Account account) {

    }

    public void deleteAccount(Account account) {

    }

    public void updateAccount(Account account) {

    }
}
