package br.com.borges.moises.expensetracker.transactions;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import br.com.borges.moises.expensetracker.dashboard.DashboardActivity;
import br.com.borges.moises.expensetracker.sharedUI.SingleFragmentWithFabActivity;

/**
 * Created by moise on 06/01/2016.
 */
public class TransactionsActivity extends SingleFragmentWithFabActivity {
    @Override
    protected Fragment getFragment() {
        return TransactionsFragment.newInstance();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context,TransactionsActivity.class);

    }
}
