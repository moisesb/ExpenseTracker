package br.com.borges.moises.expensetracker.addtransaction;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import br.com.borges.moises.expensetracker.model.Account;
import br.com.borges.moises.expensetracker.model.Category;
import br.com.borges.moises.expensetracker.model.TransactionCategory;

/**
 * Created by moise on 15/01/2016.
 */
public interface AddExpenseContract {

    interface View {
        void alertDescriptionRequied();
        void alertDateRequied();
        void alertAmountRequied();
        void setAccountsAdapter(@NonNull List<Account> accounts);
        void setCategoriesAdapter(List<Category> categories);
        void close();
    }

    interface UserActionsListener {
        void loadInitialData();
        void saveExpense(String description, String amount, String date, Account account, Category category);
    }
}
