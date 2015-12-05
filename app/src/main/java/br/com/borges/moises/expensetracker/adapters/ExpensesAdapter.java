package br.com.borges.moises.expensetracker.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.borges.moises.expensetracker.R;
import br.com.borges.moises.expensetracker.model.Expense;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Moisés on 05/12/2015.
 */
public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ExpenseViewHolder> {

    private List<Expense> mExpenses;

    public ExpensesAdapter(List<Expense> expenses) {
        mExpenses = expenses;
    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_item, parent, false);

        return new ExpenseViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ExpenseViewHolder holder, int position) {
        holder.bindExpense(mExpenses.get(position));
    }

    @Override
    public int getItemCount() {
        return mExpenses.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.expense_item_description_text_view)
        TextView mDescriptionTextView;

        @Bind(R.id.expense_item_amount_text_view)
        TextView mAmountTextView;

        public ExpenseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindExpense(Expense expense) {
            mDescriptionTextView.setText(expense.getDescription());
            mAmountTextView.setText(Double.toString(expense.getAmount()));
        }
    }
}
