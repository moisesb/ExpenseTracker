package br.com.borges.moises.expensetracker.sharedUI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import br.com.borges.moises.expensetracker.R;
import butterknife.ButterKnife;

/**
 * Created by moise on 22/12/2015.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        addFragment();
    }

    private void addFragment() {
        Fragment fragment = getFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.single_activity_frame_layout,fragment)
                .commit();
    }

    protected abstract Fragment getFragment();
}
