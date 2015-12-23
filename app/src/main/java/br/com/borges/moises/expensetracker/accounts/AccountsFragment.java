package br.com.borges.moises.expensetracker.accounts;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.borges.moises.expensetracker.R;
import br.com.borges.moises.expensetracker.db.dao.AccountRepository;
import br.com.borges.moises.expensetracker.listeners.RecyclerItemClickListener;
import br.com.borges.moises.expensetracker.model.Account;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountsFragment extends Fragment implements AccountsContract.View{

    private AccountsContract.UserActionsListener mUserActionsListener;
    private AccountsAdapter mAccountsAdapter;

    @Bind(R.id.items_list_recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.add_item_fab)
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

        mAccountsAdapter = new AccountsAdapter(new ArrayList<Account>(0), new AccountItemListener() {
            @Override
            public void onAccountClick(Account account) {
                mUserActionsListener.openAccountDetails(account);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserActionsListener.loadAccounts();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view_and_fob,container,false);
        ButterKnife.bind(this, view);

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

    }

    @Override
    public void showAccountDetailUI(@NonNull Account account) {

    }

    public interface AccountItemListener {
        void onAccountClick(Account account);
    }

    private static class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.ViewHolder> {

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

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private AccountItemListener mAccountItemListener;
            private Account mAccount;

            public ViewHolder(View itemView, AccountItemListener accountItemListener) {
                super(itemView);
                mAccountItemListener = accountItemListener;
                ButterKnife.bind(this,itemView);
            }

            public void bindAccount(Account account) {
                //TODO: fill account view
                mAccount = account;
            }

            @Override
            public void onClick(View v) {
                mAccountItemListener.onAccountClick(mAccount);
            }
        }
    }
}
