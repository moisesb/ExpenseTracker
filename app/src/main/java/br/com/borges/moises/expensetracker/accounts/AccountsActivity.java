package br.com.borges.moises.expensetracker.accounts;

import android.support.v4.app.Fragment;

import br.com.borges.moises.expensetracker.sharedUI.SingleFragmentWithFabActivity;

/**
 * Created by moise on 22/12/2015.
 */
public class AccountsActivity extends SingleFragmentWithFabActivity {
    @Override
    protected Fragment getFragment() {
        return AccountsFragment.newInstance();
    }
}
