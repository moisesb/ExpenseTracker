package br.com.borges.moises.expensetracker.addtransaction;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;

import br.com.borges.moises.expensetracker.R;
import br.com.borges.moises.expensetracker.sharedUI.SingleFragmentWithFabAndTabsActivity;
import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * Created by moise on 21/01/2016.
 */
public class AddTransactionActivity extends SingleFragmentWithFabAndTabsActivity {

    @BindString(R.string.expense_tab_title)
    String mExpenseTabTitle;

    @BindString(R.string.income_tab_title)
    String mIncomeTabTitle;

    @BindString(R.string.transfer_tab_title)
    String mTransferTabTitle;

    @Override
    protected PagerAdapter getPageAdapter() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(AddExpenseFragment.newInstance(), mExpenseTabTitle);
        viewPagerAdapter.addFragment(AddIncomeFragment.newInstance(), mIncomeTabTitle);
        viewPagerAdapter.addFragment(AddTransferFragment.newInstance(), mTransferTabTitle);
        return viewPagerAdapter;
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context,AddTransactionActivity.class);
        return intent;
    }
}
