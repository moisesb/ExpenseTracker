package br.com.borges.moises.expensetracker.db.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.borges.moises.expensetracker.db.DbSchema.AccountTable;
import br.com.borges.moises.expensetracker.db.DbSchema.UserTable;
import br.com.borges.moises.expensetracker.db.DbSchema.TransactionTable;

/**
 * Created by Moisés on 15/12/2015.
 */
public class ExpenseTrackerBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "expenseTrackerBase.db";

    private static ExpenseTrackerBaseHelper mBaseHelper;

    private ExpenseTrackerBaseHelper(Context context) {
        super(context, DATABASE_NAME , null, VERSION);
    }

    public static ExpenseTrackerBaseHelper getBaseHelper(Context context) {
        if (mBaseHelper == null) {
            mBaseHelper = new ExpenseTrackerBaseHelper(context.getApplicationContext());
        }
        return mBaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserTable.Sql.CREATE_TABLE);
        db.execSQL(AccountTable.Sql.CREATE_TABLE);
        db.execSQL(TransactionTable.Sql.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TransactionTable.Sql.CREATE_TABLE);
        db.execSQL(AccountTable.Sql.DELETE_TABLE);
        db.execSQL(UserTable.Sql.DELETE_TABLE);
        onCreate(db);
    }
}
