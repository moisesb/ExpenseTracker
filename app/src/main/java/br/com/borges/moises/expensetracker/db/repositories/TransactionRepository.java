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
import br.com.borges.moises.expensetracker.model.Category;
import br.com.borges.moises.expensetracker.model.Transaction;
import br.com.borges.moises.expensetracker.model.TransactionCategory;

/**
 * Created by Moisés on 15/12/2015.
 */
public class TransactionRepository {

    private static final String COMMA_SEP = ",";
    private static final String AS = " AS ";

    public static final String ACC_TITLE = "acc_title";
    public static final String TRANS_TYPE = "trans_type";
    public static final String TRANS_CAT = "trans_category";
    public static final String TRANS_ACCOUNT = "trans_account";
    public static final String TRANS_DATE = "trans_date";
    public static final String TRANS_DESCRIPTION = "trans_description";
    public static final String TRANS_AMOUNT = "trans_amount";
    public static final String TRANS_ID = "trans_id";
    public static final String CAT_TITLE = "cat_title";

    private static final String COLUMNS = TransactionTable.Columns.ID_WITH_PREFIX + AS + TRANS_ID + COMMA_SEP +
            TransactionTable.Columns.AMOUNT_WITH_PREFIX + AS + TRANS_AMOUNT + COMMA_SEP +
            TransactionTable.Columns.DESCRIPTION_WITH_PREFIX + AS + TRANS_DESCRIPTION + COMMA_SEP +
            TransactionTable.Columns.DATE_WITH_PREFIX + AS + TRANS_DATE + COMMA_SEP +
            TransactionTable.Columns.ACCOUNT_WITH_PREFIX + AS + TRANS_ACCOUNT + COMMA_SEP +
            TransactionTable.Columns.TYPE_WITH_PREFIX + AS + TRANS_TYPE + COMMA_SEP +
            TransactionTable.Columns.CATEGORY_WITH_PREFIX + AS + TRANS_CAT + COMMA_SEP +
            AccountTable.Columns.TITLE_WITH_PREFIX + AS + ACC_TITLE + COMMA_SEP +
            CategoryTable.Columns.DESCRIPTION_WITH_PREFIX  + AS + CAT_TITLE;

    private static final String QUERY_FIND_BY_ID = "select " + COLUMNS + " from " +
            TransactionTable.NAME + " inner join " + AccountTable.NAME +
            " on " + TransactionTable.Columns.ACCOUNT_WITH_PREFIX + " = " + AccountTable.Columns.ID_WITH_PREFIX +
            " where " + TransactionTable.Columns.ID_WITH_PREFIX + " = ?";

    private static final String QUERY_FIND_BY_TYPE = "select " + COLUMNS + " from " +
            TransactionTable.NAME + " inner join " + AccountTable.NAME +
            " on " + TransactionTable.Columns.ACCOUNT_WITH_PREFIX + " = " + AccountTable.Columns.ID_WITH_PREFIX + " " +
            " inner join " + TransactionCategoryTable.NAME +
            " on " + TransactionTable.Columns.TYPE_WITH_PREFIX + " = " + TransactionCategoryTable.Columns.ID_WITH_PREFIX +
            " inner join " + CategoryTable.NAME +
            " on " + TransactionTable.Columns.CATEGORY_WITH_PREFIX + " = " + CategoryTable.Columns.ID_WITH_PREFIX +
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
        String query = QUERY_FIND_BY_TYPE +
                " order by " + TransactionTable.Columns.ID_WITH_PREFIX + " desc";
        String[] selectionArgs = new String[] {String.valueOf(typeId)};
        Cursor c = mDatabase.rawQuery(query, selectionArgs);
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
        Category category = new Category();
        Transaction transaction = new Transaction();
        account.setId(c.getInt(c.getColumnIndex(TRANS_ID)));
        account.setTitle(c.getString(c.getColumnIndex(ACC_TITLE)));
        category.setId(c.getInt(c.getColumnIndex(TRANS_CAT)));
        category.setDescription(c.getString(c.getColumnIndex(CAT_TITLE)));
        transaction.setCategory(category);
        transaction.setId(c.getInt(c.getColumnIndex(TRANS_ID)));
        transaction.setAccount(account);
        transaction.setAmount(c.getDouble(c.getColumnIndex(TRANS_AMOUNT)));
        transaction.setDescription(c.getString(c.getColumnIndex(TRANS_DESCRIPTION)));
        try {
            transaction.setDate(mDateFormat.parse(c.getString(c.getColumnIndex(TRANS_DATE))));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        transaction.setType(TransactionCategory.fromId(c.getInt(c.getColumnIndex(TRANS_TYPE))));

        return transaction;
    }

    public void addTransaction(Transaction transaction) {
        ContentValues contentValues = getContentValues(transaction);
        mDatabase.insertOrThrow(TransactionTable.NAME, null, contentValues);
    }

    public void deleteTransaction(Transaction transaction) {
        String whereClause = TransactionTable.Columns.ID + " = ?";
        String whereArgs[] = new String[] {String.valueOf(transaction.getId())};
        mDatabase.delete(TransactionTable.NAME,whereClause,whereArgs);
    }

    @NonNull
    private ContentValues getContentValues(Transaction transaction) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TransactionTable.Columns.AMOUNT, transaction.getAmount());
        contentValues.put(TransactionTable.Columns.TYPE,transaction.getType().getValue());
        contentValues.put(TransactionTable.Columns.DESCRIPTION,transaction.getDescription());
        contentValues.put(TransactionTable.Columns.ACCOUNT,transaction.getAccount().getId());
        contentValues.put(TransactionTable.Columns.DATE, mDateFormat.format(transaction.getDate()));
        contentValues.put(TransactionTable.Columns.CATEGORY, transaction.getCategory().getId());
        return contentValues;
    }

    public static TransactionRepository getTransactionRepository(Context context) {
        if (sTransactionRepository == null) {
            sTransactionRepository = new TransactionRepository(context);
        }

        return sTransactionRepository;
    }
}
