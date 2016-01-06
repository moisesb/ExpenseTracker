package br.com.borges.moises.expensetracker.db.repositories;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

import br.com.borges.moises.expensetracker.model.Transaction;

/**
 * Created by Moisés on 15/12/2015.
 */
public class TransactionRepository {

    public List<Transaction> getTransactions() {
        final Transaction transaction = new Transaction();
        transaction.setDescription("Car Insurance");
        transaction.setAmount(500.75);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);
        return transactions;
    }

    public static TransactionRepository getTransactionRepository(Context context) {
        return new TransactionRepository();
    }
}
