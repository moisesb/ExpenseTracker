package br.com.borges.moises.expensetracker.accountdetail;

import java.util.List;

import br.com.borges.moises.expensetracker.db.dao.AccountRepository;
import br.com.borges.moises.expensetracker.db.dao.AccountTypeRepository;
import br.com.borges.moises.expensetracker.model.Account;
import br.com.borges.moises.expensetracker.model.AccountType;
import br.com.borges.moises.expensetracker.utils.StringUtils;

/**
 * Created by moise on 28/12/2015.
 */
public class AccountDetailPresenter implements AccountDetailContract.UserActionsListener {
    private final AccountTypeRepository mAccountTypeRepository;
    private final AccountDetailContract.View mView;
    private final AccountRepository mAccountRepository;
    private Account mAccount;

    public AccountDetailPresenter(AccountDetailContract.View view,
                                  AccountRepository accountRepository,
                                  AccountTypeRepository accountTypeRepository) {
        mView = view;
        mAccountRepository = accountRepository;
        mAccountTypeRepository = accountTypeRepository;
    }

    @Override
    public void openAccountDetail(int accountId) {
        loadAccountFromDatabase(accountId);

        if (mAccount == null)
            throw new IllegalArgumentException("Account with id " + accountId + " not found");

        List<AccountType> accountTypes = mAccountTypeRepository.getAccountTypes();
        mView.showAccountTypes(accountTypes);

        mView.setAccountDescription(mAccount.getDescription());
        mView.setAccountOpeningBalance(StringUtils.convertDoubleToString(mAccount.getOpeningBalance()));
        mView.setAccountType(mAccount.getType());
        mView.setCurrentBalance(StringUtils.convertDoubleToString(mAccount.getOpeningBalance()));
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
    public void updateAccount(int accountId, String description, int type, String openingBalance) {
        Account updatedAccount = new Account();
        updatedAccount.setDescription(description);
        updatedAccount.setType(type);
        updatedAccount.setOpeningBalance(StringUtils.convertStringToDouble(openingBalance));
        updatedAccount.setId(accountId);

        mAccountRepository.updateAccount(updatedAccount);
        mView.close();
    }
}
