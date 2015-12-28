package br.com.borges.moises.expensetracker.accountdetail;

import br.com.borges.moises.expensetracker.model.AccountType;

/**
 * Created by moise on 28/12/2015.
 */
public interface AccountDetailContract {

    interface View {
        void setAccountDescription(String description);
        void setAccountType(AccountType type);
        void setAccountOpeningBalance(double openingBalance);
        void setCurrentBalance(double currentBalance);
        void close();
    }

    interface UserActionsListener {
        void openAccountDetail(int accountId);
        void deleteAccount(int accountId);
        void updateAccount(int accountId, String description, AccountType type, double openingBalance);
    }
}
