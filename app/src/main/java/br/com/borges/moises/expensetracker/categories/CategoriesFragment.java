package br.com.borges.moises.expensetracker.categories;


import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.borges.moises.expensetracker.R;
import br.com.borges.moises.expensetracker.db.repositories.CategoryRepository;
import br.com.borges.moises.expensetracker.model.Category;
import br.com.borges.moises.expensetracker.model.CategoryType;
import butterknife.Bind;
import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.ButterKnife;

/**
 * Created by moise on 30/12/2015.
 */
public class CategoriesFragment extends Fragment implements CategoriesContract.View{

    private static final String CATEGORY_TYPE_PARAM = "br.com.borges.moises.expensetracker.categories.CategoryType";

    @Bind(R.id.items_list_recycler_view)
    RecyclerView mRecyclerView;

    private CategoryType mCategoryType;

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

    public static CategoriesFragment newInstance(@NonNull CategoryType categoryType) {
        CategoriesFragment fragment = new CategoriesFragment();

        Bundle args = new Bundle();
        args.putSerializable(CATEGORY_TYPE_PARAM,categoryType);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mCategoryType = (CategoryType) getArguments().getSerializable(CATEGORY_TYPE_PARAM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        ButterKnife.bind(this,view);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        mAddFloatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        mAddFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserActionsListener.addCategory(mCategoryType);
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
        mUserActionsListener.loadCategories(mCategoryType);
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

        @Bind(R.id.category_img)
        ImageView mImageView;

        @BindColor(R.color.lightGreen)
        int mLightGreen;

        @BindColor(R.color.red)
        int mRed;

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

            GradientDrawable drawable = (GradientDrawable) mImageView.getBackground();
            switch (mCategory.getCategoryType()) {
                case EXPENSE:
                    drawable.setColor(mRed);
                    break;
                case INCOME:
                    drawable.setColor(mLightGreen);
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onCategoryClick(mCategory);
        }
    }
}
