package br.com.borges.moises.expensetracker.db.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static br.com.borges.moises.expensetracker.db.DbSchema.*;

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
        db.execSQL(AccountTypeTable.Sql.CREATE_TABLE);
        db.execSQL(AccountTable.Sql.CREATE_TABLE);
        db.execSQL(CategoryTypeTable.Sql.CREATE_TABLE);
        db.execSQL(CategoryTable.Sql.CREATE_TABLE);
        db.execSQL(TransactionTable.Sql.CREATE_TABLE);


        insertCategoryTypeInitialData(db);
        insertCategoryInitialData(db);
        insertAccountTypeInitialData(db);
        insertAccountInitialData(db);
    }

    private void insertCategoryInitialData(SQLiteDatabase db) {
        db.execSQL(CategoryTable.Sql.INSERT_INITIAL_DATA,CategoryTable.Sql.getInsertParams(1,"Car",1));
        db.execSQL(CategoryTable.Sql.INSERT_INITIAL_DATA,CategoryTable.Sql.getInsertParams(2,"Travel",1));
        db.execSQL(CategoryTable.Sql.INSERT_INITIAL_DATA,CategoryTable.Sql.getInsertParams(3,"School",1));
        db.execSQL(CategoryTable.Sql.INSERT_INITIAL_DATA,CategoryTable.Sql.getInsertParams(4,"Shopping",1));
        db.execSQL(CategoryTable.Sql.INSERT_INITIAL_DATA,CategoryTable.Sql.getInsertParams(5,"Salary",2));
    }

    private void insertCategoryTypeInitialData(SQLiteDatabase db) {
        db.execSQL(CategoryTypeTable.Sql.INSERT_INITIAL_DATA,
                CategoryTypeTable.Sql.getInsertParams(CategoryTypeTable.Values.EXPENSE_ID, "Expense"));
        db.execSQL(CategoryTypeTable.Sql.INSERT_INITIAL_DATA,
                CategoryTypeTable.Sql.getInsertParams(CategoryTypeTable.Values.INCOME_ID, "Income"));
    }

    private void insertAccountTypeInitialData(SQLiteDatabase db) {
        db.execSQL(AccountTypeTable.Sql.INSERT_INITIAL_DATA, new Object[] {1, "Checking Account"});
        db.execSQL(AccountTypeTable.Sql.INSERT_INITIAL_DATA, new Object[] {2, "Cash"});
        db.execSQL(AccountTypeTable.Sql.INSERT_INITIAL_DATA, new Object[] {3, "Savings"});
        db.execSQL(AccountTypeTable.Sql.INSERT_INITIAL_DATA, new Object[] {4, "Investment"});
        db.execSQL(AccountTypeTable.Sql.INSERT_INITIAL_DATA, new Object[] {5, "Credit Card"});
    }

    private void insertAccountInitialData(SQLiteDatabase db) {
        db.execSQL(AccountTable.Sql.INSERT_INITIAL_DATA,new Object[] {1, "My Wallet", 450.35 , 2} );
        db.execSQL(AccountTable.Sql.INSERT_INITIAL_DATA,new Object[] {2, "My Bank", 5780.50 , 1} );
        db.execSQL(AccountTable.Sql.INSERT_INITIAL_DATA,new Object[] {3, "My Credit Card", -500.70 , 5} );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TransactionTable.Sql.CREATE_TABLE);
        db.execSQL(AccountTable.Sql.DELETE_TABLE);
        db.execSQL(UserTable.Sql.DELETE_TABLE);
        db.execSQL(AccountTypeTable.Sql.DELETE_TABLE);
        onCreate(db);
    }
}
