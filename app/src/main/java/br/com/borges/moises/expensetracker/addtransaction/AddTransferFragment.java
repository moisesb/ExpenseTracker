package br.com.borges.moises.expensetracker.addtransaction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.borges.moises.expensetracker.R;
import butterknife.ButterKnife;

/**
 * Created by moise on 26/01/2016.
 */
public class AddTransferFragment extends Fragment {

    public AddTransferFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_transfer,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    public static Fragment newInstance() {
        return new AddTransferFragment();
    }

}
