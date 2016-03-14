package br.com.borges.moises.expensetracker.dashboard;

import java.util.List;

import br.com.borges.moises.expensetracker.db.repositories.AccountRepository;
import br.com.borges.moises.expensetracker.db.repositories.TransactionRepository;
import br.com.borges.moises.expensetracker.model.Account;
import br.com.borges.moises.expensetracker.model.Transaction;

/**
 * Created by Moisés on 07/03/2016.
 */
public class BalanceOverviewPresenter implements BalanceOverviewContract.UserActionsListener{

    private final TransactionRepository mTransactionRepository;
    private final AccountRepository mAccountRepository;
    private final BalanceOverviewContract.View mView;

    public BalanceOverviewPresenter(BalanceOverviewContract.View view,
                                    AccountRepository accountRepository,
                                    TransactionRepository transactionRepository) {
        mView = view;
        mAccountRepository = accountRepository;
        mTransactionRepository = transactionRepository;
    }

    @Override
    public void calculateBalance(int month, int year) {
        double openingBalance = getOpeningBalance();
        double transactionsAmount = getTransactionsAmount();

        mView.showAccountsBalance(openingBalance + transactionsAmount);
    }

    private double getTransactionsAmount() {
        return mTransactionRepository.getSumofAllTransactions();
    }

    private double getOpeningBalance() {
        List<Account> accounts = mAccountRepository.getAccounts();
        double openingBalance = 0;
        for (Account account: accounts) {
            openingBalance += account.getOpeningBalance();
        }
        return openingBalance;
    }


}
