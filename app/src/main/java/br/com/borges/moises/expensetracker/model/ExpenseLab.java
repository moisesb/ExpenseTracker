package br.com.borges.moises.expensetracker.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.borges.moises.expensetracker.db.DbSchema.ExpenseTable;
import br.com.borges.moises.expensetracker.db.ExpenseBaseHelper;

/**
 * Created by Moisés on 07/12/2015.
 */
public class ExpenseLab {

    private static ExpenseLab mExpenseLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private ExpenseLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ExpenseBaseHelper(context)
                .getWritableDatabase();
    }

    public static ExpenseLab getExpenseLab(Context context) {
        if (mExpenseLab == null) {
            mExpenseLab = new ExpenseLab(context);
        }
        return mExpenseLab;
    }

    public Expense getExpense(int id) {
        return null;
    }

    public List<Expense> getExpenses() {
        final String[] projection = new String[] {
                ExpenseTable.Columns.ID,
                ExpenseTable.Columns.DESCRIPTION,
                ExpenseTable.Columns.AMOUNT };

        Cursor cursor = getCursor(null, null);

        try {
            List<Expense> expenses = new ArrayList<>();
            cursor.moveToFirst();
            do {
                Expense expense = getExpense(cursor);
                expenses.add(expense);
            } while (cursor.moveToNext());
            return expenses;
        }catch (Exception e) {
            return new ArrayList<>();
        }

    }

    private Expense getExpense(Cursor cursor) {
        Expense expense = new Expense();
        int id = cursor.getInt(cursor.getColumnIndex(ExpenseTable.Columns.ID));
        expense.setId(id);
        String description = cursor.getString(cursor.getColumnIndex(ExpenseTable.Columns.DESCRIPTION));
        expense.setDescription(description);
        double amount = cursor.getDouble(cursor.getColumnIndex(ExpenseTable.Columns.AMOUNT));
        expense.setAmount(amount);
        return expense;
    }

    private Cursor getCursor(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ExpenseTable.NAME,  // The table to query
                null,                               // The columns to return
                whereClause,                                       // The columns for the WHERE clause
                whereArgs,                                   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        return cursor;
    }

    public void addExpense(Expense expense) {
        if (expense != null) {
            ContentValues contentValues = getContentValues(expense);

            long newRowID = mDatabase.insert(ExpenseTable.NAME, null, contentValues);
        }
    }

    public void deleteExpense(Expense expense) {
        if (expense != null) {
            String selection = ExpenseTable.Columns.ID + "like ?";
            String[] selectionArgs = { String.valueOf(expense.getId()) };
            mDatabase.delete(ExpenseTable.NAME, selection, selectionArgs);
        }
    }

    public void updateExpense(Expense expense) {
        String id = Integer.toString(expense.getId());

        ContentValues contentValues = getContentValues(expense);

        mDatabase.update(ExpenseTable.NAME
                ,contentValues
                ,ExpenseTable.Columns.ID + " = ?"
                ,new String[] {id});
    }

    @NonNull
    private ContentValues getContentValues(Expense expense) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ExpenseTable.Columns.DESCRIPTION, expense.getDescription());
        contentValues.put(ExpenseTable.Columns.AMOUNT, expense.getAmount());
        return contentValues;
    }




}
