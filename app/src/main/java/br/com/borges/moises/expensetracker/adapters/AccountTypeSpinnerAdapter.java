package br.com.borges.moises.expensetracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.borges.moises.expensetracker.model.AccountType;

/**
 * Created by moise on 29/12/2015.
 */
public class AccountTypeSpinnerAdapter extends BaseAdapter {

    private List<AccountType> mAccountTypes;

    public AccountTypeSpinnerAdapter(List<AccountType> accountTypes) {
        mAccountTypes = accountTypes;
    }

    public void setAccountTypes(List<AccountType> accountTypes) {
        mAccountTypes = accountTypes;
        notifyDataSetChanged();
    }

    public int getPosition(int accountTypeId) {
        for (AccountType accountType: mAccountTypes) {
            if (accountType.getId() == accountTypeId)
                return mAccountTypes.indexOf(accountType);
        }
        return -1;
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
        TextView textView = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1,parent,false);
        AccountType accountType = mAccountTypes.get(position);
        textView.setText(accountType.getDescription());
        return textView;
    }
}
