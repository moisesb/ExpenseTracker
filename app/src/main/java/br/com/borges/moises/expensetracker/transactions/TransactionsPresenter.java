package br.com.borges.moises.expensetracker.transactions;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.borges.moises.expensetracker.db.repositories.TransactionRepository;
import br.com.borges.moises.expensetracker.model.Transaction;
import br.com.borges.moises.expensetracker.model.TransactionType;

/**
 * Created by moise on 06/01/2016.
 */
public class TransactionsPresenter implements TransactionsContract.UserActionsListener {

    private TransactionsContract.View mView;
    private TransactionRepository mTransactionRepository;

    public TransactionsPresenter(TransactionsContract.View view,
                                  TransactionRepository transactionRepository) {
        mView = view;
        mTransactionRepository = transactionRepository;
    }

    @Override
    public void loadTransactions() {
        List<Transaction> transactions = mTransactionRepository.getTransactions(TransactionType.EXPENSE.getValue());
        mView.showTransactions(transactions);
    }

    @Override
    public void openTransactionDetails(@NonNull Transaction transaction) {
        if (transaction != null) {
            mView.showTransactionDetails(transaction.getId());
        }
    }

    @Override
    public void addTransaction() {
        mView.showAddTransaction();
    }
}
