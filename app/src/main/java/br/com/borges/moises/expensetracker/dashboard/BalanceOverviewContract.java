package br.com.borges.moises.expensetracker.dashboard;

import java.util.Date;

/**
 * Created by Moisés on 07/03/2016.
 */
public interface BalanceOverviewContract {

    interface View {
        void showAccountsBalance(double balance);
        void showSavingsBalance(double balance);
    }

    interface UserActionsListener {
        void calculateBalance(int month, int year);
    }
}
