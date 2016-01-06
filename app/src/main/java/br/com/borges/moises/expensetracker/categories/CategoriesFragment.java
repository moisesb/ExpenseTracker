package br.com.borges.moises.expensetracker.categories;


import android.os.Bundle;
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
import br.com.borges.moises.expensetracker.db.repositories.CategoryRepository;
import br.com.borges.moises.expensetracker.model.Category;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by moise on 30/12/2015.
 */
public class CategoriesFragment extends Fragment implements CategoriesContract.View{

    @Bind(R.id.items_list_recycler_view)
    RecyclerView mRecyclerView;

    private FloatingActionButton mAddFloatingActionButton;
    private CategoriesContract.UserActionsListener mUserActionsListener;
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {
        @Override
        public void onCategoryClick(Category category) {
            mUserActionsListener.openCategoryDetails(category);
        }
    };

    private CategoryAdapter mAdapter =
            new CategoryAdapter(new ArrayList<Category>(0),mOnItemClickListener);

    public static CategoriesFragment newInstance() {
        return new CategoriesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        ButterKnife.bind(this,view);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        mAddFloatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.add_item_fab);
        mAddFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserActionsListener.addCategory();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUserActionsListener = new CategoriesPresenter(this,
                CategoryRepository.getCategoryRepository(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserActionsListener.loadCategories();
    }

    @Override
    public void showCategories(List<Category> categories) {
        mAdapter.replaceData(categories);
    }

    @Override
    public void showAddCategory() {
        Log.d("Categories","showing add category");
    }

    @Override
    public void showCategoryDetails(int categoryId) {
        Log.d("Categories","showind category id " + categoryId + " details");
    }

    public interface OnItemClickListener {
        void onCategoryClick(Category category);
    }

    public static class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

        private List<Category> mCategories;
        private OnItemClickListener mOnItemClickListener;

        public CategoryAdapter(List<Category> categories, OnItemClickListener onItemClickListener) {
            mCategories = categories;
            mOnItemClickListener = onItemClickListener;
        }

        public void replaceData(List<Category> categories) {
            mCategories = categories;
            notifyDataSetChanged();
        }

        @Override
        public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.category_item,parent,false);

            return new CategoryViewHolder(view,mOnItemClickListener);
        }

        @Override
        public void onBindViewHolder(CategoryViewHolder holder, int position) {
            Category category = mCategories.get(position);
            holder.bindCategory(category);
        }

        @Override
        public int getItemCount() {
            return mCategories.size();
        }
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.category_description_text_view)
        TextView mDescriptionTextView;

        private Category mCategory;
        private OnItemClickListener mOnItemClickListener;

        public CategoryViewHolder(View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mOnItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
        }

        public void bindCategory(Category category) {
            mCategory = category;
            mDescriptionTextView.setText(category.getDescription());
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onCategoryClick(mCategory);
        }
    }
}
