package br.com.borges.moises.expensetracker;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import br.com.borges.moises.expensetracker.model.Expense;
import br.com.borges.moises.expensetracker.ui.expenses.ExpensesFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExpensesActivity extends AppCompatActivity {

    @Bind(R.id.expenses_toolbar) Toolbar mExpensesToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        ButterKnife.bind(this);
        
        bindToolbar();

        addExpensesFragment();
    }

    private void addExpensesFragment() {
        Expense sampleExpense = new Expense();
        sampleExpense.setAmount(12.60);
        sampleExpense.setDescription("Lanche");
        sampleExpense.setId(1);
        sampleExpense.setAccount(null);

        Expense anotherSampleExpense = new Expense();
        anotherSampleExpense.setAmount(119.90);
        anotherSampleExpense.setDescription("Xbox Gold");
        anotherSampleExpense.setId(2);
        anotherSampleExpense.setAccount(null);

        List<Expense> expenses = new ArrayList<>();
        expenses.add(sampleExpense);
        expenses.add(anotherSampleExpense);

        Fragment expensesFragment = ExpensesFragment.newInstance(expenses);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.expenses_frame_layout,expensesFragment)
                .commit();

    }

    private void bindToolbar() {
        setSupportActionBar(mExpensesToolbar);
    }

    @OnClick(R.id.add_expense_fab) void openAddExpenseActivity() {
        Intent intent = ExpenseActivity.newIntent(this,new Expense());
        startActivity(intent);
    }
}
