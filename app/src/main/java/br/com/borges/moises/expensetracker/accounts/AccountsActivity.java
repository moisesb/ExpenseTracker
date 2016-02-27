package br.com.borges.moises.expensetracker.accounts;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import br.com.borges.moises.expensetracker.dashboard.DashboardActivity;
import br.com.borges.moises.expensetracker.sharedUI.SingleFragmentWithFabActivity;

/**
 * Created by moise on 22/12/2015.
 */
public class AccountsActivity extends SingleFragmentWithFabActivity {
    @Override
    protected Fragment getFragment() {
        return AccountsFragment.newInstance();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context,AccountsActivity.class);
    }
}
