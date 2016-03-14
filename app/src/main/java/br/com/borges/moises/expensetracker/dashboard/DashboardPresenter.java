package br.com.borges.moises.expensetracker.dashboard;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moisés on 05/03/2016.
 */
public class DashboardPresenter implements DashboardContract.UserActionsListener {


    private final DashboardContract.View mView;

    public DashboardPresenter(DashboardContract.View view) {
        mView = view;
    }

    @Override
    public void loadCards() {
        List<OverviewCard> overviewCards = new ArrayList<>();
        overviewCards.add(new OverviewCard() {
            @Override
            public Fragment getFragment() {
                return BalanceOverviewFragment.newInstance();
            }
        });

        mView.showOverviewCards(overviewCards);
    }
}
