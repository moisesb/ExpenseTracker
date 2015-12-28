package br.com.borges.moises.expensetracker.accounts;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.borges.moises.expensetracker.model.Account;

/**
 * Created by moise on 22/12/2015.
 */
public interface AccountsContract {

    interface View {
        void showAccounts(List<Account> accounts);
        void showAddAccount();
        void showAccountDetailUI(int accountId);
    }

    interface UserActionsListener {
        void loadAccounts();
        void addAccount();
        void openAccountDetails(@NonNull Account account);
    }
}
