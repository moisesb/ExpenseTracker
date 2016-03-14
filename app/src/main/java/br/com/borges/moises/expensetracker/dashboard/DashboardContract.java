package br.com.borges.moises.expensetracker.dashboard;

import java.util.List;

/**
 * Created by moise on 30/12/2015.
 */
public interface DashboardContract {

    interface View {
        void showOverviewCards(List<OverviewCard> overviewCards);
    }

    interface UserActionsListener {
        void loadCards();
    }
}
