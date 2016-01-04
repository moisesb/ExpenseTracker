package br.com.borges.moises.expensetracker.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.borges.moises.expensetracker.R;
import butterknife.Bind;

/**
 * Created by moise on 04/01/2016.
 */
public abstract class ViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ItemClickListener<T> mItemClickListener;
    private T mItem;
    private View mView;

    public ViewHolder(View itemView, ItemClickListener<T> itemClickListener) {
        super(itemView);
        mView = itemView;
        mItemClickListener = itemClickListener;
        bindView(itemView);
        itemView.setOnClickListener(this);
    }

    protected abstract void bindView(View itemView);

    public void bindItem(T item) {
        mItem = item;
        bindItemWithView(item);
    }

    protected abstract void bindItemWithView(T item);

    @Override
    public void onClick(View v) {
        mItemClickListener.onItemClick(mItem);
    }
}
