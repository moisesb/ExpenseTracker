package br.com.borges.moises.expensetracker.addtransaction;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.borges.moises.expensetracker.db.repositories.AccountRepository;
import br.com.borges.moises.expensetracker.db.repositories.CategoryRepository;
import br.com.borges.moises.expensetracker.db.repositories.TransactionRepository;
import br.com.borges.moises.expensetracker.model.Account;
import br.com.borges.moises.expensetracker.model.Category;
import br.com.borges.moises.expensetracker.model.CategoryType;
import br.com.borges.moises.expensetracker.model.Transaction;
import br.com.borges.moises.expensetracker.model.TransactionCategory;
import br.com.borges.moises.expensetracker.utils.DateUtils;

/**
 * Created by moise on 27/01/2016.
 */
public class AddExpensePresenter implements AddExpenseContract.UserActionsListener {

    private TransactionRepository mTransactionRepository;
    private CategoryRepository mCategoryRepository;
    private AccountRepository mAccountRepository;
    private AddExpenseContract.View mView;
    private DateUtils mDateUtils = new DateUtils();

    public AddExpensePresenter(@NonNull AddExpenseContract.View view,
                               @NonNull TransactionRepository transactionRepository,
                               @NonNull CategoryRepository categoryRepository,
                               @NonNull AccountRepository accountRepository) {
        mTransactionRepository = transactionRepository;
        mCategoryRepository = categoryRepository;
        mAccountRepository = accountRepository;
        mView = view;
    }

    @Override
    public void loadInitialData() {
        List<Category> categories = mCategoryRepository.getExpenseCategories();
        List<Account> accounts = mAccountRepository.getAccounts();
        mView.setAccountsAdapter(accounts);
        mView.setCategoriesAdapter(categories);
    }

    @Override
    public void saveExpense(String description, String amount, String date, Account account, Category category) {
        if (isNullOrEmpty(description)) {
            mView.alertDescriptionRequied();
            return;
        }
        if (isNotAValidNumber(amount)) {
            mView.alertAmountRequied();
            return;
        }

        Date transactionDate = mDateUtils.getDate(date);
        if (isNotAValidDate(transactionDate)) {
            mView.alertDateRequied();
            return;
        }
        Transaction expense = new Transaction();
        expense.setDescription(description);
        expense.setAmount(Double.valueOf(amount));
        expense.setDate(transactionDate);
        expense.setAccount(account);
        expense.setType(TransactionCategory.EXPENSE);
        expense.setCategory(category);

        mTransactionRepository.addTransaction(expense);

        mView.close();
    }

    private boolean isNotAValidDate(Date date) {
        return date == null;
    }

    private boolean isNotAValidNumber(String value) {

        return false;
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
