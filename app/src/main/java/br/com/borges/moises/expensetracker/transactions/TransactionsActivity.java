package br.com.borges.moises.expensetracker.transactions;

import android.support.v4.app.Fragment;

import br.com.borges.moises.expensetracker.sharedUI.SingleFragmentWithFabActivity;

/**
 * Created by moise on 06/01/2016.
 */
public class TransactionsActivity extends SingleFragmentWithFabActivity {
    @Override
    protected Fragment getFragment() {
        return TransactionFragment.newInstance();
    }
}
