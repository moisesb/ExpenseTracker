package br.com.borges.moises.expensetracker.ui.expenses;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.io.Serializable;

import br.com.borges.moises.expensetracker.R;
import br.com.borges.moises.expensetracker.model.Expense;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExpenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpenseFragment extends Fragment {

    @Bind(R.id.description_edit_text)
    EditText mDescriptionEditText;

    @Bind(R.id.amount_edit_text)
    EditText mAmountEditText;

    private static final String ARG_EXPENSE =
            "br.com.borges.moises.ExpenseTracker.ui.expenses.ExpenseFragment.expense";
    private static final String ARG_CALLBACKS =
            "br.com.borges.moises.ExpenseTracker.ui.expenses.ExpenseFragment.callback";

    private Expense mExpense;
    private ExpenseFragmentCallbacks mCallbacks;

    public interface ExpenseFragmentCallbacks extends Serializable {
        void addExpense(Expense expense);
        void removeExpense(Expense expense);
        void updateExpense(Expense expense);
    }


    public ExpenseFragment() {
        // Required empty public constructor
    }

    public static ExpenseFragment newInstance(ExpenseFragmentCallbacks callbacks, Expense expense) {
        ExpenseFragment fragment = new ExpenseFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_EXPENSE, expense);
        args.putSerializable(ARG_CALLBACKS, callbacks);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mExpense = (Expense) getArguments().getSerializable(ARG_EXPENSE);
            mCallbacks = (ExpenseFragmentCallbacks) getArguments().getSerializable(ARG_CALLBACKS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_expense, container, false);
        ButterKnife.bind(this,view);

        bindExpense();
        addListeners();

        return view;
    }

    private void addListeners() {
        mDescriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mExpense.setDescription(s.toString());
            }
        });

        mAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    double amount = Double.valueOf(s.toString());
                    mExpense.setAmount(amount);
                }catch (NumberFormatException e) {
                    mExpense.setAmount(0);
                }
            }
        });
    }

    private void bindExpense() {
        mDescriptionEditText.setText(mExpense.getDescription());
        mAmountEditText.setText(Double.toString(mExpense.getAmount()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_expense,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_add_expense:
                mCallbacks.addExpense(mExpense);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.add_expense_button) void addExpense() {
        if (mExpense.isValid()) {
            mCallbacks.addExpense(mExpense);
        }
    }

}
