package br.com.borges.moises.expensetracker.categories;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.borges.moises.expensetracker.db.repositories.CategoryRepository;
import br.com.borges.moises.expensetracker.model.Category;
import br.com.borges.moises.expensetracker.model.CategoryType;

/**
 * Created by moise on 30/12/2015.
 */
public class CategoriesPresenter implements CategoriesContract.UserActionsListener {

    private CategoriesContract.View mView;
    private CategoryRepository mCategoryRepository;

    public CategoriesPresenter(@NonNull CategoriesContract.View view,
                               @NonNull CategoryRepository categoryRepository) {
        mView = view;
        mCategoryRepository = categoryRepository;
    }

    @Override
    public void loadCategories(@NonNull CategoryType categoryType) {
        List<Category> categories = mCategoryRepository.getCategories(categoryType);
        mView.showCategories(categories);
    }

    @Override
    public void addCategory(@NonNull CategoryType categoryType) {
        mView.showAddCategory();
    }

    @Override
    public void openCategoryDetails(@NonNull Category category) {
        mView.showCategoryDetails(category.getId());
    }
}
