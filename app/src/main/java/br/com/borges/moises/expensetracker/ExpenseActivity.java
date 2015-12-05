package br.com.borges.moises.expensetracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import br.com.borges.moises.expensetracker.model.Expense;

public class ExpenseActivity extends Activity {

    private static final String ARG_EXPENSE = "br.com.borges.moises.expensetracker.ExpenseActivity.expense";

    private Expense mExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        Bundle bundle = getIntent().getExtras();
        if (bundle.getSerializable(ARG_EXPENSE) != null) {
            mExpense = (Expense) bundle.getSerializable(ARG_EXPENSE);
        }

    }

    public static Intent newIntent(Context context,Expense expense) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_EXPENSE, expense);

        Intent intent = new Intent(context, ExpenseActivity.class);
        intent.putExtras(args);
        return intent;

    }
}
