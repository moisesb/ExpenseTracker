package br.com.borges.moises.expensetracker.accountdetail;

import br.com.borges.moises.expensetracker.db.dao.AccountRepository;
import br.com.borges.moises.expensetracker.model.Account;
import br.com.borges.moises.expensetracker.model.AccountType;

/**
 * Created by moise on 28/12/2015.
 */
public class AccountDetailPresenter implements AccountDetailContract.UserActionsListener {
    private AccountDetailContract.View mView;
    private AccountRepository mAccountRepository;
    private Account mAccount;

    public AccountDetailPresenter(AccountDetailContract.View view, AccountRepository accountRepository) {
        mView = view;
        mAccountRepository = accountRepository;
    }

    @Override
    public void openAccountDetail(int accountId) {
        loadAccountFromDatabase(accountId);

        if (mAccount == null)
            throw new IllegalArgumentException("Account with id " + accountId + " not found");

        mView.setAccountDescription(mAccount.getDescription());
        mView.setAccountOpeningBalance(mAccount.getOpeningBalance());
        mView.setAccountType(mAccount.getType());
        mView.setCurrentBalance(mAccount.getOpeningBalance());
    }

    private void loadAccountFromDatabase(int accountId) {
        mAccount = mAccountRepository.getAccount(accountId);
    }

    @Override
    public void deleteAccount(int accountId) {
        if (mAccount == null)
            loadAccountFromDatabase(accountId);

        mAccountRepository.deleteAccount(mAccount);
        mView.close();
    }

    @Override
    public void updateAccount(int accountId, String description, AccountType type, double openingBalance) {
        Account updatedAccount = new Account();
        updatedAccount.setDescription(description);
        updatedAccount.setType(type);
        updatedAccount.setOpeningBalance(openingBalance);
        updatedAccount.setId(accountId);

        mAccountRepository.updateAccount(updatedAccount);
        mView.close();
    }
}
