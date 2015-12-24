package br.com.borges.moises.expensetracker.sharedUI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import br.com.borges.moises.expensetracker.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by moise on 22/12/2015.
 */
public abstract class SingleFragmentWithFabActivity extends AppCompatActivity {

    @Bind(R.id.items_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment_with_fab);
        ButterKnife.bind(this);

        initActionBar();
    }

    private void initActionBar() {
        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        addFragment();
    }

    private void addFragment() {
        Fragment fragment = getFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.single_fragment_framelayout,fragment)
                .commit();
    }

    protected abstract Fragment getFragment();
}
