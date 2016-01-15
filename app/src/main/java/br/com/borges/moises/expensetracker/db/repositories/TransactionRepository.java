package br.com.borges.moises.expensetracker.db.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static br.com.borges.moises.expensetracker.db.DbSchema.*;

import br.com.borges.moises.expensetracker.db.ExpenseTrackerBaseHelper;
import br.com.borges.moises.expensetracker.model.Account;
import br.com.borges.moises.expensetracker.model.Transaction;
import br.com.borges.moises.expensetracker.model.TransactionType;

/**
 * Created by Moisés on 15/12/2015.
 */
public class TransactionRepository {

    private static final String COMMA_SEP = ",";
    private static final String AS = " AS ";

    public static final String ACC_TITLE = "acc_title";
    public static final String TRANS_TYPE = "trans_type";
    public static final String TRANS_ACCOUNT = "trans_account";
    public static final String TRANS_DATE = "trans_date";
    public static final String TRANS_DESCRIPTION = "trans_description";
    public static final String TRANS_AMOUNT = "trans_amount";
    public static final String TRANS_ID = "trans_id";

    private static final String COLUMNS = TransactionTable.Columns.ID_WITH_PREFIX + AS + TRANS_ID + COMMA_SEP +
            TransactionTable.Columns.AMOUNT_WITH_PREFIX + AS + TRANS_AMOUNT + COMMA_SEP +
            TransactionTable.Columns.DESCRIPTION_WITH_PREFIX + AS + TRANS_DESCRIPTION + COMMA_SEP +
            TransactionTable.Columns.DATE_WITH_PREFIX + AS + TRANS_DATE + COMMA_SEP +
            TransactionTable.Columns.ACCOUNT_WITH_PREFIX + AS + TRANS_ACCOUNT + COMMA_SEP +
            TransactionTable.Columns.TYPE_WITH_PREFIX + AS + TRANS_TYPE + COMMA_SEP +
            AccountTable.Columns.TITLE_WITH_PREFIX + AS + ACC_TITLE;

    private static final String QUERY_FIND_BY_ID = "select " + COLUMNS + " from " +
            TransactionTable.NAME + " inner join " + AccountTable.NAME +
            " on " + TransactionTable.Columns.ACCOUNT_WITH_PREFIX + " = " + AccountTable.Columns.ID_WITH_PREFIX +
            " where " + TransactionTable.Columns.ID_WITH_PREFIX + " = ?";

    private static final String QUERY_FIND_BY_TYPE = "select " + COLUMNS + " from " +
            TransactionTable.NAME + " inner join " + AccountTable.NAME +
            " on " + TransactionTable.Columns.ACCOUNT_WITH_PREFIX + " = " + AccountTable.Columns.ID_WITH_PREFIX + " " +
            " inner join " + TransactionCategoryTable.NAME +
            " on " + TransactionTable.Columns.TYPE_WITH_PREFIX + " = " + TransactionCategoryTable.Columns.ID_WITH_PREFIX +
            " where " + TransactionCategoryTable.Columns.ID_WITH_PREFIX + " = ?";

    private static TransactionRepository sTransactionRepository;
    private static SQLiteQueryBuilder sQueryBuilder;

    private SQLiteDatabase mDatabase;
    private final DateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    static {
        sQueryBuilder = new SQLiteQueryBuilder();
    }


    private TransactionRepository(Context context) {
        mDatabase = ExpenseTrackerBaseHelper.getBaseHelper(context)
                    .getWritableDatabase();
    }


    public List<Transaction> getTransactions(int typeId) {
        String[] selectionArgs = new String[] {String.valueOf(typeId)};
        Cursor c = mDatabase.rawQuery(QUERY_FIND_BY_TYPE, selectionArgs);
        c.moveToFirst();

        List<Transaction> transactions = new ArrayList<>();
        while(!c.isAfterLast()) {
            transactions.add(getTransaction(c));
            c.moveToNext();
        }
        return transactions;
    }

    public Transaction getTransaction(String id) {

        String[] selectionArgs = new String[] {id};
        Cursor c = mDatabase.rawQuery(QUERY_FIND_BY_ID, selectionArgs);
        c.moveToFirst();
        Transaction transaction = getTransaction(c);

        return transaction;
    }

    private Transaction getTransaction(Cursor c) {
        Account account = new Account();
        Transaction transaction = new Transaction();

        account.setTitle(c.getString(c.getColumnIndex(ACC_TITLE)));
        transaction.setId(c.getInt(c.getColumnIndex(TRANS_ID)));
        transaction.setAccount(account);
        transaction.setAmount(c.getDouble(c.getColumnIndex(TRANS_AMOUNT)));
        transaction.setDescription(c.getString(c.getColumnIndex(TRANS_DESCRIPTION)));
        try {
            transaction.setDate(mDateFormat.parse(c.getString(c.getColumnIndex(TRANS_DATE))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        transaction.setType(TransactionType.fromId(c.getInt(c.getColumnIndex(TRANS_TYPE))));

        return transaction;
    }

    public void addTransaction(Transaction transaction) {
        ContentValues contentValues = getContentValues(transaction);
        mDatabase.insert(TransactionTable.NAME,null,contentValues);
    }

    public void deleteTransaction(Transaction transaction) {
        String whereClause = TransactionTable.Columns.ID + " = ?";
        String whereArgs[] = new String[] {String.valueOf(transaction.getId())};
        mDatabase.delete(TransactionTable.NAME,whereClause,whereArgs);
    }

    @NonNull
    private ContentValues getContentValues(Transaction transaction) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TransactionTable.Columns.ID, transaction.getId());
        contentValues.put(TransactionTable.Columns.AMOUNT, transaction.getAmount());
        contentValues.put(TransactionTable.Columns.TYPE,transaction.getType().getValue());
        contentValues.put(TransactionTable.Columns.DESCRIPTION,transaction.getDescription());
        contentValues.put(TransactionTable.Columns.ACCOUNT,transaction.getAccount().getId());
        contentValues.put(TransactionTable.Columns.DATE, mDateFormat.format(transaction.getDate()));
        return contentValues;
    }

    public static TransactionRepository getTransactionRepository(Context context) {
        if (sTransactionRepository == null) {
            sTransactionRepository = new TransactionRepository(context);
        }

        return sTransactionRepository;
    }
}
