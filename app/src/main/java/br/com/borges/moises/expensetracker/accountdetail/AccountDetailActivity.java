package br.com.borges.moises.expensetracker.accountdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import br.com.borges.moises.expensetracker.sharedUI.SingleFragmentActivity;

/**
 * Created by moise on 28/12/2015.
 */
public class AccountDetailActivity extends SingleFragmentActivity{

    private static final String ACCOUNT_ID_EXTRA = "br.com.borges.moises.expensetracker.sharedUI.SingleFragmentActivity.AccountId";
    private int mAccountId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAccountId = getIntent().getIntExtra(ACCOUNT_ID_EXTRA, -1);
    }

    @Override
    protected Fragment getFragment() {
        return null;
    }

    public static Intent newIntent(Context context, int accountId) {
        Intent intent = new Intent(context,AccountDetailActivity.class);
        intent.putExtra(ACCOUNT_ID_EXTRA,accountId);
        return intent;
    }
}
