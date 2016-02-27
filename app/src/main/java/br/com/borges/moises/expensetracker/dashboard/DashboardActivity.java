package br.com.borges.moises.expensetracker.dashboard;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import br.com.borges.moises.expensetracker.R;
import br.com.borges.moises.expensetracker.accounts.AccountsActivity;
import br.com.borges.moises.expensetracker.categories.CategoriesActivity;
import br.com.borges.moises.expensetracker.transactions.TransactionsActivity;
import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * Created by Moisés on 21/02/2016.
 */
public class DashboardActivity extends AppCompatActivity {

    @BindString(R.string.app_name)
    String mAppName;

    @Bind(R.id.dashboard_drawer_layout)
    DrawerLayout mDrawerLayout;

    @Bind(R.id.dashboard_toolbar)
    Toolbar mToolbar;

    @Bind(R.id.nav_view)
    NavigationView mNavigationView;

    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        configActionBar();

        setupNavigationDrawer();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupNavigationDrawer() {

        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                R.string.dashboard_drawer_open,
                R.string.dashboard_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setSupportActionBarTitle(mAppName);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                setSupportActionBarTitle(mAppName);
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                return onNavigationDrawerItemSelected(item);
            }
        });
    }

    private void setSupportActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            invalidateOptionsMenu();
        }
    }

    private boolean onNavigationDrawerItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_accounts:
                return openAccounts();
            case R.id.navigation_categories:
                return openCategories();
            case R.id.navigation_transactions:
                return openTransactions();
            default:
                return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean openTransactions() {
        Intent intent = TransactionsActivity.newIntent(this);
        startActivity(intent);
        return true;
    }

    private boolean openCategories() {
        Intent intent = CategoriesActivity.newIntent(this);
        startActivity(intent);
        return true;
    }

    private boolean openAccounts() {
        Log.d("Dashboard", "Accounts");
        Intent intent = AccountsActivity.newIntent(this);
        startActivity(intent);
        return true;
    }

    private void configActionBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

}
