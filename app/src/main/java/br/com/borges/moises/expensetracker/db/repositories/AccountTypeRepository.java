package br.com.borges.moises.expensetracker.db.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import static br.com.borges.moises.expensetracker.db.DbSchema.*;
import br.com.borges.moises.expensetracker.db.ExpenseTrackerBaseHelper;
import br.com.borges.moises.expensetracker.model.AccountType;

/**
 * Created by moise on 28/12/2015.
 */
public class AccountTypeRepository {

    private static AccountTypeRepository sAccountTypeRepository;
    private SQLiteDatabase mDatabase;

    private AccountTypeRepository(Context context) {
        mDatabase = ExpenseTrackerBaseHelper.getBaseHelper(context)
                .getWritableDatabase();
    }

    public static AccountTypeRepository getAccountTypeRepository(Context context) {
        if (sAccountTypeRepository == null)
            sAccountTypeRepository = new AccountTypeRepository(context);

        return sAccountTypeRepository;
    }

    public List<AccountType> getAccountTypes() {
        Cursor c = mDatabase.query(AccountTypeTable.NAME,null,null,null,null,null,null);

        c.moveToFirst();
        List<AccountType> accountTypes = new ArrayList<>();
        while (!c.isAfterLast()) {
            AccountType accountType = getAccountType(c);
            accountTypes.add(accountType);
            c.moveToNext();
        }
        return accountTypes;
    }

    private AccountType getAccountType(Cursor cursor) {
        AccountType accountType = new AccountType();
        accountType.setId(cursor.getInt(cursor.getColumnIndex(AccountTypeTable.Columns.ID)));
        accountType.setDescription(cursor.getString(cursor.getColumnIndex(AccountTypeTable.Columns.DESCRIPTION)));
        return accountType;
    }

    public AccountType getAccountType(int id) {
        String selection = AccountTypeTable.Columns.ID + " = ?";
        String selectionArgs[] = new String[] {String.valueOf(id)};
        Cursor cursor = mDatabase.query(AccountTypeTable.NAME,null,selection,selectionArgs,null,null,null);
        cursor.moveToFirst();

        return getAccountType(cursor);
    }

    public void addAccountType(@NonNull AccountType accountType) {
        ContentValues contentValues = getContentValues(accountType);
        mDatabase.insert(AccountTypeTable.NAME,null,contentValues);
    }

    private ContentValues getContentValues(AccountType accountType) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AccountTypeTable.Columns.ID,accountType.getId());
        contentValues.put(AccountTypeTable.Columns.DESCRIPTION,accountType.getDescription());
        return contentValues;
    }

    public void deleteAccountType(@NonNull AccountType accountType) {
        String whereClause = AccountTypeTable.Columns.ID + " = ?";
        String[] whereArgs = new String[] {String.valueOf(accountType.getId())};
        mDatabase.delete(AccountTypeTable.NAME,whereClause, whereArgs);
    }

    public void updateAccountType(@NonNull AccountType accountType) {
        ContentValues contentValues = getContentValues(accountType);
        String whereClause = AccountTypeTable.Columns.ID + " = ?";
        String[] whereArgs = new String[] {String.valueOf(accountType.getId())};
        mDatabase.update(AccountTypeTable.NAME,contentValues,whereClause,whereArgs);
    }

}
