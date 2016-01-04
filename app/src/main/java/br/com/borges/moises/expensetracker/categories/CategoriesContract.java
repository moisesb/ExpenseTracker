package br.com.borges.moises.expensetracker.categories;

import java.util.List;

import br.com.borges.moises.expensetracker.model.Category;

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
        void loadCategories();
        void addCategory();
        void openCategoryDetails(Category category);
    }
}
