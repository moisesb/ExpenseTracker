package br.com.borges.moises.expensetracker.addtransaction;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.borges.moises.expensetracker.R;
import br.com.borges.moises.expensetracker.adapters.ItemClickListener;
import br.com.borges.moises.expensetracker.db.repositories.AccountRepository;
import br.com.borges.moises.expensetracker.db.repositories.CategoryRepository;
import br.com.borges.moises.expensetracker.db.repositories.TransactionRepository;
import br.com.borges.moises.expensetracker.dialogs.CalculatorDialog;
import br.com.borges.moises.expensetracker.model.Account;
import br.com.borges.moises.expensetracker.model.Category;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by moise on 25/01/2016.
 */
public class AddExpenseFragment extends Fragment implements AddExpenseContract.View, ItemClickListener<Category>, DatePickerDialog.OnDateSetListener {

    public static final String CALCULATOR = "Calculator";
    @Bind(R.id.expense_description)
    EditText mDescriptionEditText;

    @Bind(R.id.expense_amount)
    EditText mAmountEditText;

    @Bind(R.id.expense_category)
    Spinner mCategorySpinner;

    @Bind(R.id.expense_account)
    Spinner mAccountSpinner;

    @Bind(R.id.expense_date)
    EditText mDateEditText;

    private Category mCategory;
    private AddExpenseContract.UserActionsListener mUserActionsListener;

    private CategorySpinnerAdapter mCategorySpinnerAdapter =
            new CategorySpinnerAdapter(new ArrayList<Category>(0));

    private AccountSpinnerAdapter mAccountSpinnerAdapter =
            new AccountSpinnerAdapter(new ArrayList<Account>(0));


    public AddExpenseFragment() {

    }

    public static Fragment newInstance() {
        return new AddExpenseFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUserActionsListener = new AddExpensePresenter(this,
                TransactionRepository.getTransactionRepository(getContext()),
                CategoryRepository.getCategoryRepository(getContext()),
                AccountRepository.getAccountRepository(getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserActionsListener.loadInitialData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_expense_or_income, container, false);
        ButterKnife.bind(this, view);
        mCategorySpinner.setAdapter(mCategorySpinnerAdapter);
        mAccountSpinner.setAdapter(mAccountSpinnerAdapter);
        mAmountEditText.setClickable(true);
        mDateEditText.setClickable(true);
        FloatingActionButton addExpenseActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        addExpenseActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExpense();
            }
        });
        return view;
    }

    private void saveExpense() {
        mUserActionsListener.saveExpense(mDescriptionEditText.getText().toString(),
                mAmountEditText.getText().toString(),
                mDateEditText.getText().toString(),
                (Account) mAccountSpinner.getSelectedItem(),
                (Category) mCategorySpinner.getSelectedItem());
    }

    @OnClick(R.id.expense_amount) void onAmountClick() {
        DialogFragment dialogFragment = new CalculatorDialog();
        dialogFragment.show(getFragmentManager(), "Calculator");
    }

    @OnClick(R.id.expense_date)
    void OnDateClick() {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog pickerDialog = new DatePickerDialog(getContext(), this, year, month, day);
        pickerDialog.show();
    }

    @Override
    public void alertDescriptionRequied() {

    }

    @Override
    public void alertDateRequied() {

    }

    @Override
    public void alertAmountRequied() {

    }

    @Override
    public void setAccountsAdapter(@NonNull List<Account> accounts) {
        mAccountSpinnerAdapter.replaceData(accounts);
    }

    @Override
    public void setCategoriesAdapter(List<Category> categories) {
        mCategorySpinnerAdapter.replaceData(categories);
    }

    @Override
    public void close() {
        getActivity().finish();
    }


    @Override
    public void onItemClick(Category item) {
        mCategory = item;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mDateEditText.setText(formatDate(year, monthOfYear, dayOfMonth));
    }

    @NonNull
    private String formatDate(int year, int monthOfYear, int dayOfMonth) {
        return dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
    }

    public static class AccountSpinnerAdapter extends BaseAdapter {

        private List<Account> mAccounts;

        public AccountSpinnerAdapter(List<Account> accounts) {
            mAccounts = accounts;
        }

        public void replaceData(List<Account> accounts) {
            mAccounts = accounts;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mAccounts.size();
        }

        @Override
        public Object getItem(int position) {
            return mAccounts.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.account_title_item, parent, false);
            TextView accountTitleTextView = (TextView) view.findViewById(R.id.account_title_text_view);
            Account account = (Account) getItem(position);
            accountTitleTextView.setText(account.getTitle());
            return view;
        }
    }

    public static class CategorySpinnerAdapter extends BaseAdapter {

        private List<Category> mCategories;

        public CategorySpinnerAdapter(List<Category> categories) {
            mCategories = categories;
        }

        public void replaceData(List<Category> categories) {
            mCategories = categories;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mCategories.size();
        }

        @Override
        public Object getItem(int position) {
            return mCategories.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.category_item, parent, false);
            TextView categoryDescriptionTextView = (TextView) view.findViewById(R.id.category_description_text_view);
            Category category = (Category) getItem(position);
            categoryDescriptionTextView.setText(category.getDescription());
            return view;
        }
    }
}
