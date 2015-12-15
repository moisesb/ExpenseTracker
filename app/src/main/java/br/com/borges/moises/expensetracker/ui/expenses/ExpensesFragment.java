package br.com.borges.moises.expensetracker.ui.expenses;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.List;

import br.com.borges.moises.expensetracker.ExpenseActivity;
import br.com.borges.moises.expensetracker.R;
import br.com.borges.moises.expensetracker.adapters.ExpensesAdapter;
import br.com.borges.moises.expensetracker.listeners.RecyclerItemClickListener;
import br.com.borges.moises.expensetracker.model.Expense;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ExpensesFragment extends Fragment {

    private static final String ARG_EXPENSES = "br.com.borges.moises.expensetracker.ui.expenses.expensesList";

    private List<Expense> mExpenses;
    @Bind(R.id.expenses_list_recycler_view)
    RecyclerView mExpensesRecyclerView;

    public ExpensesFragment() {
        // Required empty public constructor
    }


    public static ExpensesFragment newInstance(List<Expense> expenses) {
        ExpensesFragment fragment = new ExpensesFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_EXPENSES,(Serializable) expenses);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mExpenses = (List<Expense>) getArguments().getSerializable(ARG_EXPENSES);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);
        ButterKnife.bind(this,view);

        initExpensesRecyclerView();

        return view;
    }

    private void initExpensesRecyclerView() {
        mExpensesRecyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mExpensesRecyclerView.setLayoutManager(layoutManager);

        ExpensesAdapter adapter = new ExpensesAdapter(mExpenses);
        mExpensesRecyclerView.setAdapter(adapter);

        mExpensesRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Expense selectedExpense = mExpenses.get(position);
                Intent intent = ExpenseActivity.newIntent(getActivity(), selectedExpense);
                startActivity(intent);
            }
        }));
    }

}
