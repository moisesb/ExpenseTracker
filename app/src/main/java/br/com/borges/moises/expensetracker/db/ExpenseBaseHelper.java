package br.com.borges.moises.expensetracker.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.borges.moises.expensetracker.db.DbSchema.ExpenseTable;

/**
 * Created by Moisés on 07/12/2015.
 */
public class ExpenseBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "expenseBase.db";

    private static final String SQL_CREATE_DATABASE = "create table " + ExpenseTable.NAME + "("
            + ExpenseTable.Columns.ID + " integer primary key autoincrement,"
            + ExpenseTable.Columns.DESCRIPTION + ","
            + ExpenseTable.Columns.AMOUNT + ")";

    private static final String SQL_DELETE_DATABASE = "drop table if exists " + ExpenseTable.NAME;

    public ExpenseBaseHelper(Context context) {
        super(context, DATABASE_NAME , null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_DATABASE);
        onCreate(db);
    }
}
