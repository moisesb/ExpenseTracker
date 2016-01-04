package br.com.borges.moises.expensetracker.categories;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.borges.moises.expensetracker.db.dao.CategoryRepository;
import br.com.borges.moises.expensetracker.model.Category;

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
    public void loadCategories() {
        List<Category> categories = mCategoryRepository.getCategories();
        mView.showCategories(categories);
    }

    @Override
    public void addCategory() {
        mView.showAddCategory();
    }

    @Override
    public void openCategoryDetails(@NonNull Category category) {
        mView.showCategoryDetails(category.getId());
    }
}
