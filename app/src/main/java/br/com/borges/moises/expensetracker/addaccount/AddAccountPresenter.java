package br.com.borges.moises.expensetracker.addaccount;

import java.util.List;

import br.com.borges.moises.expensetracker.addaccount.AddAccountContract.UserActionsListener;
import br.com.borges.moises.expensetracker.db.dao.AccountRepository;
import br.com.borges.moises.expensetracker.db.dao.AccountTypeRepository;
import br.com.borges.moises.expensetracker.model.Account;
import br.com.borges.moises.expensetracker.model.AccountType;
import br.com.borges.moises.expensetracker.utils.StringUtils;

/**
 * Created by moise on 29/12/2015.
 */
public class AddAccountPresenter implements UserActionsListener {

    private AddAccountContract.View mView;
    private AccountTypeRepository mAccountTypeRepository;
    private AccountRepository mAccountRepository;

    public AddAccountPresenter(AddAccountContract.View view,
                               AccountRepository accountRepository,
                               AccountTypeRepository accountTypeRepository) {
        mView = view;
        mAccountRepository = accountRepository;
        mAccountTypeRepository = accountTypeRepository;
    }

    @Override
    public void loadInitalData() {
        List<AccountType> accountTypes = mAccountTypeRepository.getAccountTypes();
        mView.setAccountTypesAdapter(accountTypes);
    }

    @Override
    public void addAcccount(String description, String openingBalance, int accountType) {
        if (description != null &&
                !description.isEmpty() &&
                openingBalance != null &&
                !openingBalance.isEmpty() &&
                accountType > -1) {
            Account account = new Account();
            account.setDescription(description);
            account.setOpeningBalance(StringUtils.convertStringToDouble(openingBalance));
            account.setType(accountType);
            mAccountRepository.addAccount(account);
            mView.close();
        }else {
            mView.showMissingInformationDialog();
        }
    }
}
