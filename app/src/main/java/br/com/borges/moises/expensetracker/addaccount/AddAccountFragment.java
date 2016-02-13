package br.com.borges.moises.expensetracker.addaccount;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.borges.moises.expensetracker.R;
import br.com.borges.moises.expensetracker.adapters.AccountTypeSpinnerAdapter;
import br.com.borges.moises.expensetracker.db.repositories.AccountRepository;
import br.com.borges.moises.expensetracker.db.repositories.AccountTypeRepository;
import br.com.borges.moises.expensetracker.model.AccountType;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by moise on 29/12/2015.
 */
public class AddAccountFragment extends Fragment implements AddAccountContract.View{

    @Bind(R.id.title_edit_text)
    EditText mDescriptionEditText;

    @Bind(R.id.opening_balance_edit_text)
    EditText mOpeningBalanceEditText;

    @Bind(R.id.account_type_spinner)
    Spinner mAccountTypeSpinner;

    @Bind(R.id.current_balance_text_view)
    TextView mCurrentBalanceTextView;

    private AddAccountContract.UserActionsListener mUserActionsListener;

    private AccountTypeSpinnerAdapter mAdapter = new AccountTypeSpinnerAdapter(new ArrayList<AccountType>(0));

    public static AddAccountFragment newInstance() {
        AddAccountFragment fragment = new AddAccountFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_detail,container,false);
        ButterKnife.bind(this,view);

        mAccountTypeSpinner.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUserActionsListener = new AddAccountPresenter(this,
                AccountRepository.getAccountRepository(getActivity()),
                AccountTypeRepository.getAccountTypeRepository(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserActionsListener.loadInitalData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add_item,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_add_item:
                mUserActionsListener.addAcccount(mDescriptionEditText.getText().toString(),
                        mOpeningBalanceEditText.getText().toString(),
                        ((AccountType) mAccountTypeSpinner.getSelectedItem()).getId());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showMissingInformationDialog() {

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
