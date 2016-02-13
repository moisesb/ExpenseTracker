package br.com.borges.moises.expensetracker.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import br.com.borges.moises.expensetracker.R;
import br.com.borges.moises.expensetracker.model.Category;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by moise on 01/02/2016.
 */
public abstract class CategoryRecyclerViewAdapter {

    public static ItemsRecyclerViewAdapter<Category> getCategoryRecyclerViewAdapter(@NonNull List<Category> items,
                                                                                    @NonNull ItemClickListener<Category> itemClickListener) {
        return new CategoryAdapter(items,itemClickListener);
    }

    public static class CategoryAdapter extends ItemsRecyclerViewAdapter<Category> {

        public CategoryAdapter(List<Category> items, ItemClickListener<Category> itemListener) {
            super(items, itemListener);
        }

        @Override
        protected int getViewFromLayout() {
            return R.layout.category_item;
        }

        @NonNull
        @Override
        protected ViewHolder<Category> getViewHolder(View itemView) {
            return new CategoryAdapterViewHolder(itemView,getItemListener());
        }
    }

    public static class CategoryAdapterViewHolder extends ViewHolder<Category> {

        @Bind(R.id.category_description_text_view)
        TextView mDescriptionTextView;

        public CategoryAdapterViewHolder(View itemView, ItemClickListener<Category> itemClickListener) {
            super(itemView, itemClickListener);
        }

        @Override
        protected void bindView(View itemView) {
            ButterKnife.bind(this,itemView);
        }

        @Override
        protected void bindItemWithView(Category item) {
            mDescriptionTextView.setText(item.getDescription());
        }
    }
}
