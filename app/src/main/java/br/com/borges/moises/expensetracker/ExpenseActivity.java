package br.com.borges.moises.expensetracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.borges.moises.expensetracker.model.Expense;
import br.com.borges.moises.expensetracker.model.ExpenseLab;
import br.com.borges.moises.expensetracker.ui.expenses.ExpenseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ExpenseActivity extends AppCompatActivity implements ExpenseFragment.ExpenseFragmentCallbacks {

    private static final String ARG_EXPENSE = "br.com.borges.moises.expensetracker.ExpenseActivity.expense";

    @Bind(R.id.expense_toolbar)
    Toolbar mExpenseToolbar;

    private Expense mExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle.getSerializable(ARG_EXPENSE) != null) {
            mExpense = (Expense) bundle.getSerializable(ARG_EXPENSE);
            bindToolbar();
            showExpenseFragment();
        }
    }

    private void bindToolbar() {
        setSupportActionBar(mExpenseToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public static Intent newIntent(Context context,Expense expense) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_EXPENSE, expense);

        Intent intent = new Intent(context, ExpenseActivity.class);
        intent.putExtras(args);
        return intent;

    }


    private void showExpenseFragment() {
        Fragment expenseFragmentx = ExpenseFragment.newInstance(this, mExpense);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.expense_frame_layout, expenseFragmentx)
                .commit();
    }


    @Override
    public void addExpense(Expense expense) {
        ExpenseLab.getExpenseLab(this).addExpense(expense);
        finish();
    }

    @Override
    public void removeExpense(Expense expense) {
        // TODO: Implement ASAP
    }

    @Override
    public void updateExpense(Expense expense) {
        ExpenseLab.getExpenseLab(this).updateExpense(expense);
    }
}
