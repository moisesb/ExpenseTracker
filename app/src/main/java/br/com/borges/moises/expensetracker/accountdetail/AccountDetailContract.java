package br.com.borges.moises.expensetracker.accountdetail;

import java.util.List;

import br.com.borges.moises.expensetracker.model.AccountType;

/**
 * Created by moise on 28/12/2015.
 */
public interface AccountDetailContract {

    interface View {
        void setAccountDescription(String description);
        void setAccountType(int type);
        void setAccountOpeningBalance(String openingBalance);
        void setCurrentBalance(String currentBalance);
        void setAccountTypesAdapter(List<AccountType> accountTypes);
        void close();
    }

    interface UserActionsListener {
        void openAccountDetail(int accountId);
        void deleteAccount(int accountId);
        void updateAccount(int accountId, String description, int type, String openingBalance);
    }
}
