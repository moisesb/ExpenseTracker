package br.com.borges.moises.expensetracker.transactions;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.borges.moises.expensetracker.model.Transaction;

/**
 * Created by moise on 06/01/2016.
 */
public interface TransactionsContract {

    interface View {
        void showTransactions(List<Transaction> transtions);
        void showTransactionDetails(int transactionId);
        void showAddTransaction();
    }

    interface UserActionsListener {
        void loadTransactions();
        void openTransactionDetails(@NonNull Transaction transaction);
        void addTransaction();
    }
}
