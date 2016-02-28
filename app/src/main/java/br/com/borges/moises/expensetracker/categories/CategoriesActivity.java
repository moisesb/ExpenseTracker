package br.com.borges.moises.expensetracker.categories;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;

import br.com.borges.moises.expensetracker.R;
import br.com.borges.moises.expensetracker.addtransaction.AddExpenseFragment;
import br.com.borges.moises.expensetracker.dashboard.DashboardActivity;
import br.com.borges.moises.expensetracker.model.CategoryType;
import br.com.borges.moises.expensetracker.sharedUI.SingleFragmentWithFabActivity;
import br.com.borges.moises.expensetracker.sharedUI.SingleFragmentWithFabAndTabsActivity;
import butterknife.BindString;

/**
 * Created by moise on 30/12/2015.
 */
public class CategoriesActivity extends SingleFragmentWithFabAndTabsActivity {

    @BindString(R.string.income_tab_title)
    String mIncomeTabTitle;

    @BindString(R.string.expense_tab_title)
    String mExpenseTabTitle;

    public static Intent newIntent(Context context) {
        return new Intent(context,CategoriesActivity.class);
    }

    @Override
    protected PagerAdapter getPageAdapter() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(CategoriesFragment.newInstance(CategoryType.EXPENSE), mExpenseTabTitle);
        viewPagerAdapter.addFragment(CategoriesFragment.newInstance(CategoryType.INCOME), mIncomeTabTitle);
        return viewPagerAdapter;
    }
}
