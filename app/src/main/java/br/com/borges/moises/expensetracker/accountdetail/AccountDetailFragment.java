package br.com.borges.moises.expensetracker.accountdetail;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import br.com.borges.moises.expensetracker.R;
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

        return view;
    }


    @Override
    public void setAccountDescription(String description) {

    }

    @Override
    public void setAccountType(AccountType type) {

    }

    @Override
    public void setAccountOpeningBalance(double openingBalance) {

    }

    @Override
    public void setCurrentBalance(double currentBalance) {

    }

    @Override
    public void close() {
        getActivity().finish();
    }

    public static class AccountTypeSpinnerAdapter extends BaseAdapter {

        private List<AccountType> mAccountTypes;

        public AccountTypeSpinnerAdapter(List<AccountType> accountTypes) {
            mAccountTypes = accountTypes;
        }

        @Override
        public int getCount() {
            return mAccountTypes.size();
        }

        @Override
        public Object getItem(int position) {
            return mAccountTypes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            return null;
        }
    }
}
