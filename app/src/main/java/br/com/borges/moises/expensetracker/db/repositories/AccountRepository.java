package br.com.borges.moises.expensetracker.db.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.borges.moises.expensetracker.db.DbSchema.AccountTable;
import br.com.borges.moises.expensetracker.db.ExpenseTrackerBaseHelper;
import br.com.borges.moises.expensetracker.model.Account;

/**
 * Created by Moisés on 15/12/2015.
 */
public class AccountRepository{

    private static AccountRepository sAccountRepository;
    private SQLiteDatabase sDatabase;

    private AccountRepository(Context context) {
        sDatabase = ExpenseTrackerBaseHelper.getBaseHelper(context)
                .getWritableDatabase();
    }

    public static AccountRepository getAccountRepository(Context context) {
        if (sAccountRepository == null) {
            sAccountRepository = new AccountRepository(context);
        }

        return sAccountRepository;
    }

    public Account getAccount(int id) {
        String selection = AccountTable.Columns.ID + " = ?";
        String[] selectionArgs = new String[] {String.valueOf(id)};
        Cursor c = sDatabase.query(
                AccountTable.NAME,  // The table to query
                null,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        c.moveToFirst();
        Account account = getAccount(c);

        return account;
    }

    private Account getAccount(Cursor cursor) {
        Account account = new Account();
        account.setId(cursor.getInt(cursor.getColumnIndex(AccountTable.Columns.ID)));
        account.setDescription(cursor.getString(cursor.getColumnIndex(AccountTable.Columns.DESCRIPTION)));
        account.setOpeningBalance(cursor.getDouble(cursor.getColumnIndex(AccountTable.Columns.OPENING_BALANCE)));
        account.setType(cursor.getInt(cursor.getColumnIndex(AccountTable.Columns.TYPE)));
        return account;
    }

    public List<Account> getAccounts() {
        Cursor c = sDatabase.query(
                AccountTable.NAME,  // The table to query
                null,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        List<Account> accounts = new ArrayList<>();
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Account account = getAccount(c);
            accounts.add(account);
            c.moveToNext();
        }

        return accounts;
    }

    public long addAccount(Account account) {
        ContentValues contentValues = getContentValues(account);

        long newRowId = sDatabase.insert(AccountTable.NAME,null,contentValues);
        return newRowId;
    }

    public void deleteAccount(Account account) {
        if (account != null) {
            String selection = AccountTable.Columns.ID + " like ?";
            String[] selectionArgs = { String.valueOf(account.getId()) };
            sDatabase.delete(AccountTable.NAME, selection, selectionArgs);
        }
    }

    public void updateAccount(Account account) {
        String id = Integer.toString(account.getId());

        ContentValues contentValues = getContentValues(account);

        sDatabase.update(AccountTable.NAME
                ,contentValues
                ,AccountTable.Columns.ID + " = ?"
                ,new String[] {id});
    }

    @NonNull
    private ContentValues getContentValues(Account account) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AccountTable.Columns.DESCRIPTION, account.getDescription());
        contentValues.put(AccountTable.Columns.TYPE, account.getType());
        contentValues.put(AccountTable.Columns.OPENING_BALANCE, account.getOpeningBalance());
        return contentValues;
    }

}
