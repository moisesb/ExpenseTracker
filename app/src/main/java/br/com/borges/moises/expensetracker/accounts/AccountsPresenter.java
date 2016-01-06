package br.com.borges.moises.expensetracker.accounts;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.borges.moises.expensetracker.db.repositories.AccountRepository;
import br.com.borges.moises.expensetracker.model.Account;


/**
 * Created by moise on 22/12/2015.
 */
public class AccountsPresenter implements AccountsContract.UserActionsListener {

    private AccountsContract.View mAccountsContractView;
    private AccountRepository mAccountRepository;

    public AccountsPresenter(@NonNull AccountsContract.View accountsContractView,
                             @NonNull AccountRepository accountRepository) {
        mAccountsContractView = accountsContractView;
        mAccountRepository = accountRepository;
    }

    @Override
    public void loadAccounts() {
        List<Account> accounts = mAccountRepository.getAccounts();
        mAccountsContractView.showAccounts(accounts);
    }

    @Override
    public void addAccount() {
        mAccountsContractView.showAddAccount();
    }

    @Override
    public void openAccountDetails(@NonNull Account account) {
        if (account != null) {
            mAccountsContractView.showAccountDetailUI(account.getId());
        }else {
            throw new IllegalArgumentException("Account cannot be null");
        }
    }
}
