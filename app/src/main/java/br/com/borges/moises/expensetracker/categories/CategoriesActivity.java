package br.com.borges.moises.expensetracker.categories;

import android.support.v4.app.Fragment;

import br.com.borges.moises.expensetracker.sharedUI.SingleFragmentWithFabActivity;

/**
 * Created by moise on 30/12/2015.
 */
public class CategoriesActivity extends SingleFragmentWithFabActivity {
    @Override
    protected Fragment getFragment() {
        return CategoriesFragment.newInstance();
    }
}
