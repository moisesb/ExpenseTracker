package br.com.borges.moises.expensetracker.accounts;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import br.com.borges.moises.expensetracker.R;
import br.com.borges.moises.expensetracker.accountdetail.AccountDetailActivity;
import br.com.borges.moises.expensetracker.db.dao.AccountRepository;
import br.com.borges.moises.expensetracker.model.Account;
import br.com.borges.moises.expensetracker.utils.AmountToString;
import butterknife.Bind;
import butterknife.ButterKnife;

public class AccountsFragment extends Fragment implements AccountsContract.View{

    private AccountsContract.UserActionsListener mUserActionsListener;

    private AccountsAdapter mAccountsAdapter = new AccountsAdapter(new ArrayList<Account>(0), new AccountItemListener() {
        @Override
        public void onAccountClick(Account account) {
            mUserActionsListener.openAccountDetails(account);
        }
    });

    @Bind(R.id.items_list_recycler_view)
    RecyclerView mRecyclerView;

    FloatingActionButton mFloatingActionButton;

    public static AccountsFragment newInstance() {
        AccountsFragment fragment = new AccountsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public AccountsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserActionsListener.loadAccounts();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view,container,false);
        ButterKnife.bind(this, view);

        mFloatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.add_item_fab);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAccountsAdapter);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserActionsListener.addAccount();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUserActionsListener = new AccountsPresenter(this, AccountRepository.getAccountRepository(getActivity()));
    }

    @Override
    public void showAccounts(List<Account> accounts) {
        mAccountsAdapter.replaceData(accounts);
    }

    @Override
    public void showAddAccount() {
        //TODO: open AccountActivity when available
        Log.d("Accounts", "add account");
    }

    @Override
    public void showAccountDetailUI(int accountId) {
        //TODO: open AccountDefatail when available
        Log.d("Accounts","account id " + accountId +  " detail");
        Intent intent = AccountDetailActivity.newIntent(getActivity(),accountId);
        startActivity(intent);
    }

    public interface AccountItemListener {
        void onAccountClick(Account account);
    }

    private static class AccountsAdapter extends RecyclerView.Adapter<ViewHolder> {

        private List<Account> mAccounts;
        private AccountItemListener mAccountItemListener;

        public AccountsAdapter(List<Account> accounts, AccountItemListener accountItemListener) {
            mAccounts = accounts;
            mAccountItemListener = accountItemListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View accountView = inflater.inflate(R.layout.item_account, parent, false);

            return new ViewHolder(accountView, mAccountItemListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Account account = mAccounts.get(position);
            holder.bindAccount(account);
        }

        @Override
        public int getItemCount() {
            return mAccounts.size();
        }

        public void replaceData(List<Account> accounts) {
            mAccounts = accounts;
            notifyDataSetChanged();
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private AccountItemListener mAccountItemListener;
        private Account mAccount;
        private View mView;

        @Bind(R.id.account_balance)
        TextView mBalanceTextView;

        @Bind(R.id.account_description)
        TextView mdescriptionTextView;

        @Bind(R.id.account_type_textview)
        TextView mTypeTextView;

        public ViewHolder(View itemView, AccountItemListener accountItemListener) {
            super(itemView);
            mView = itemView;
            mAccountItemListener = accountItemListener;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindAccount(Account account) {
            mAccount = account;
            mdescriptionTextView.setText(mAccount.getDescription());
            mTypeTextView.setText(String.valueOf(mAccount.getType()));
            mBalanceTextView.setText(String.valueOf(AmountToString.convert(mAccount.getOpeningBalance())));
        }

        @Override
        public void onClick(View v) {
            mAccountItemListener.onAccountClick(mAccount);
        }
    }
}
