package br.com.borges.moises.expensetracker.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.borges.moises.expensetracker.R;
import br.com.borges.moises.expensetracker.db.repositories.AccountRepository;
import br.com.borges.moises.expensetracker.db.repositories.TransactionRepository;
import br.com.borges.moises.expensetracker.utils.DateUtils;
import br.com.borges.moises.expensetracker.utils.StringUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Moisés on 05/03/2016.
 */
public class BalanceOverviewFragment extends Fragment implements BalanceOverviewContract.View{

    @Bind(R.id.balance_amont)
    TextView mAmontTextView;

    private BalanceOverviewContract.UserActionsListener mUserActionsListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_balance_overview, container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUserActionsListener = new BalanceOverviewPresenter(this,
                AccountRepository.getAccountRepository(getContext()),
                TransactionRepository.getTransactionRepository(getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        int month = DateUtils.getCurrentMonth();
        int year = DateUtils.getCurrentYear();
        mUserActionsListener.calculateBalance(month,year);
    }

    public static Fragment newInstance() {
        return new BalanceOverviewFragment();
    }

    @Override
    public void showAccountsBalance(double balance) {
        mAmontTextView.setText(StringUtils.convertDoubleToString(balance));
    }

    @Override
    public void showSavingsBalance(double balance) {

    }
}
