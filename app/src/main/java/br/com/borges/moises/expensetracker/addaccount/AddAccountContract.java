package br.com.borges.moises.expensetracker.addaccount;

import java.util.List;

import br.com.borges.moises.expensetracker.model.AccountType;
import br.com.borges.moises.expensetracker.model.Transaction;
import br.com.borges.moises.expensetracker.model.TransactionCategory;

/**
 * Created by moise on 29/12/2015.
 */
public interface AddAccountContract {

    interface View {
        void showMissingInformationDialog();
        void setAccountTypesAdapter(List<AccountType> accountTypes);
        void close();
    }

    interface UserActionsListener {
        void loadInitalData();
        void addAcccount(String description, String openingBalance, int accountType);
    }
}
