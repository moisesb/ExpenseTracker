package br.com.borges.moises.expensetracker.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import br.com.borges.moises.expensetracker.R;
import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Moisés on 01/03/2016.
 */
public class CalculatorDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_calculator, null);
        builder.setView(view);

        ButterKnife.bind(this,view);
        return builder.create();
    }

    @OnClick(R.id.calculator_cancel_button) void onCancelClick() {

    }

    @OnClick(R.id.calculator_positive_button) void onPositiveClick() {

    }

    @OnClick(R.id.calculator_digit_one) void onOneClick() {

    }

    @OnClick(R.id.calculator_digit_two) void onTwoClick() {

    }

    @OnClick(R.id.calculator_digit_three) void onThreeClick(){

    }

}
