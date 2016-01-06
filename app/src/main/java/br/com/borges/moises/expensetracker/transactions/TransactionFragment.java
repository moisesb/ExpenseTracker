package br.com.borges.moises.expensetracker.transactions;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.borges.moises.expensetracker.R;
import br.com.borges.moises.expensetracker.adapters.ItemClickListener;
import br.com.borges.moises.expensetracker.adapters.ItemsRecyclerViewAdapter;
import br.com.borges.moises.expensetracker.adapters.ViewHolder;
import br.com.borges.moises.expensetracker.db.repositories.TransactionRepository;
import br.com.borges.moises.expensetracker.model.Transaction;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by moise on 06/01/2016.
 */
public class TransactionFragment extends Fragment implements TransactionsContract.View, ItemClickListener<Transaction>{

    @Bind(R.id.items_list_recycler_view)
    RecyclerView mRecyclerView;

    private FloatingActionButton mActionButton;

    private TransactionsContract.UserActionsListener mUserActionsListener;
    private TransactionRecyclerViewAdapter mAdapter = new TransactionRecyclerViewAdapter(new ArrayList<Transaction>(0), this);

    public static Fragment newInstance() {
        return new TransactionFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUserActionsListener = new TransactionsPresenter(this,
                TransactionRepository.getTransactionRepository(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserActionsListener.loadTransactions();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        ButterKnife.bind(this, view);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        mActionButton = (FloatingActionButton) getActivity().findViewById(R.id.add_item_fab);
        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserActionsListener.addTransaction();
            }
        });


        return view;
    }


    @Override
    public void showTransactions(List<Transaction> transtions) {
        mAdapter.replaceData(transtions);
    }

    @Override
    public void showTransactionDetails(int transactionId) {
        Log.d("Transactions", "show transaction id " + transactionId);
    }

    @Override
    public void showAddTransaction() {
        Log.d("Transactions", "add new transaction");
    }

    @Override
    public void onItemClick(Transaction transaction) {
        mUserActionsListener.openTransactionDetails(transaction);
    }

    public static class TransactionRecyclerViewAdapter extends ItemsRecyclerViewAdapter<Transaction> {

        public TransactionRecyclerViewAdapter(List<Transaction> items, ItemClickListener<Transaction> itemListener) {
            super(items, itemListener);
        }

        @Override
        protected int getViewFromLayout() {
            return R.layout.transaction_item;
        }

        @NonNull
        @Override
        protected ViewHolder<Transaction> getViewHolder(View itemView) {
            return new TransactionViewHolder(itemView,getItemListener());
        }
    }

    public static class TransactionViewHolder extends ViewHolder<Transaction> {

        @Bind(R.id.transaction_description_text_view)
        TextView mDescriptionTextView;

        @Bind(R.id.transactiomn_amount_text_view)
        TextView mAmountTextView;

        public TransactionViewHolder(View itemView, ItemClickListener<Transaction> itemClickListener) {
            super(itemView, itemClickListener);
        }

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this,itemView);
        }

        @Override
        protected void bindItemWithView(Transaction transaction) {
            mDescriptionTextView.setText(transaction.getDescription());
            mAmountTextView.setText(String.valueOf(transaction.getAmount()));
        }
    }
}
