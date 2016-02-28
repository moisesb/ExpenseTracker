package br.com.borges.moises.expensetracker.categories;

import android.support.annotation.NonNull;

import java.util.List;

import br.com.borges.moises.expensetracker.model.Category;
import br.com.borges.moises.expensetracker.model.CategoryType;

/**
 * Created by moise on 30/12/2015.
 */
public interface CategoriesContract {
    interface View {
        void showCategories(List<Category> categories);
        void showAddCategory();
        void showCategoryDetails(int categoryId);

    }

    interface UserActionsListener {
        void loadCategories(@NonNull CategoryType categoryType);
        void addCategory(@NonNull CategoryType categoryType);
        void openCategoryDetails(Category category);
    }
}
