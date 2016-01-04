package br.com.borges.moises.expensetracker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.borges.moises.expensetracker.R;
import butterknife.Bind;

/**
 * Created by moise on 04/01/2016.
 */
public abstract class ItemsRecyclerViewAdapter<T> extends RecyclerView.Adapter<ViewHolder>{

    private List<T> mItems;
    private ItemClickListener<T> mItemListener;

    public ItemsRecyclerViewAdapter(List<T> items, ItemClickListener<T> itemListener) {
        mItems = items;
        mItemListener = itemListener;
    }

    public List<T> getItems() {
        return mItems;
    }

    public void setItems(List<T> items) {
        mItems = items;
    }

    public ItemClickListener<T> getItemListener() {
        return mItemListener;
    }

    public void setItemListener(ItemClickListener<T> itemListener) {
        mItemListener = itemListener;
    }

    protected abstract int getViewFromLayout();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(getViewFromLayout(), parent, false);

        return getViewHolder(itemView);
    }

    @NonNull
    protected abstract ViewHolder<T> getViewHolder(View itemView);

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        T item = mItems.get(position);
        holder.bindItem(item);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void replaceData(List<T> items) {
        mItems = items;
        notifyDataSetChanged();
    }
}




