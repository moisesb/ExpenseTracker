package br.com.borges.moises.expensetracker.categories;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import br.com.borges.moises.expensetracker.dashboard.DashboardActivity;
import br.com.borges.moises.expensetracker.sharedUI.SingleFragmentWithFabActivity;

/**
 * Created by moise on 30/12/2015.
 */
public class CategoriesActivity extends SingleFragmentWithFabActivity {
    @Override
    protected Fragment getFragment() {
        return CategoriesFragment.newInstance();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context,CategoriesActivity.class);
    }
}
