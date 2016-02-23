package br.com.borges.moises.expensetracker.dashboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.borges.moises.expensetracker.R;
import butterknife.ButterKnife;

/**
 * Created by Moisés on 21/02/2016.
 */
public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
    }
}
