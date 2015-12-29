package br.com.borges.moises.expensetracker.accountdetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.borges.moises.expensetracker.R;
import br.com.borges.moises.expensetracker.adapters.AccountTypeSpinnerAdapter;
import br.com.borges.moises.expensetracker.db.dao.AccountRepository;
import br.com.borges.moises.expensetracker.db.dao.AccountTypeRepository;
import br.com.borges.moises.expensetracker.model.AccountType;
import butterknife.Bind;
import butterknife.ButterKnife;

public class AccountDetailFragment extends Fragment implements AccountDetailContract.View{

    private static final String ARG_ACCOUNT_ID = "br.com.borges.moises.expensetracker.accountdetail.AccountId";

    private int mAccountId;

    @Bind(R.id.description_edit_text)
    EditText mDescritionEditText;

    @Bind(R.id.opening_balance_edit_text)
    EditText mOpeningBalanceEditText;

    @Bind(R.id.account_type_spinner)
    Spinner mAccountTypeSpinner;

    @Bind(R.id.current_balance_text_view)
    TextView mCurrentBalanceTextView;

    private AccountDetailContract.UserActionsListener mUserActionsListener;
    private AccountTypeSpinnerAdapter mAdapter = new AccountTypeSpinnerAdapter(new ArrayList<AccountType>(0));

    public static AccountDetailFragment newInstance(int accountId) {
        AccountDetailFragment fragment = new AccountDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ACCOUNT_ID, accountId);
        fragment.setArguments(args);
        return fragment;
    }

    public AccountDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUserActionsListener = new AccountDetailPresenter(this,
                AccountRepository.getAccountRepository(getActivity()),
                AccountTypeRepository.getAccountTypeRepository(getActivity()));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAccountId = getArguments().getInt(ARG_ACCOUNT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account_detail, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);

        mAccountTypeSpinner.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserActionsListener.openAccountDetail(mAccountId);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_edit_item, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_edit_item:
                mUserActionsListener.updateAccount(mAccountId,
                        mDescritionEditText.getText().toString(),
                        getAccoutTypeIdFromSpinner(),
                        mOpeningBalanceEditText.getText().toString());
                return true;
            case R.id.menu_action_delete_item:
                mUserActionsListener.deleteAccount(mAccountId);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private int getAccoutTypeIdFromSpinner() {
        AccountType accountType = (AccountType) mAccountTypeSpinner.getSelectedItem();

        return accountType.getId();
    }

    @Override
    public void setAccountDescription(String description) {
        mDescritionEditText.setText(description);
    }

    @Override
    public void setAccountType(int type) {
        int indexOf = mAdapter.getPosition(type);
        mAccountTypeSpinner.setSelection(indexOf);
    }

    @Override
    public void setAccountOpeningBalance(String openingBalance) {
        mOpeningBalanceEditText.setText(openingBalance);
    }

    @Override
    public void setCurrentBalance(String currentBalance) {
        mCurrentBalanceTextView.setText(currentBalance);
    }

    @Override
    public void setAccountTypesAdapter(List<AccountType> accountTypes) {
        mAdapter.setAccountTypes(accountTypes);
    }

    @Override
    public void close() {
        getActivity().finish();
    }

}
