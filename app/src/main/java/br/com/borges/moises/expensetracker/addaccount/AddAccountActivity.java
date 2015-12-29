package br.com.borges.moises.expensetracker.addaccount;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import br.com.borges.moises.expensetracker.sharedUI.SingleFragmentActivity;

/**
 * Created by moise on 29/12/2015.
 */
public class AddAccountActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setActionBarHomeButtonActive(true);
    }

    @Override
    protected Fragment getFragment() {
        return AddAccountFragment.newInstance();
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context,AddAccountActivity.class);
        return intent;
    }
}
