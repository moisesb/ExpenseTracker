package br.com.borges.moises.expensetracker.db.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static br.com.borges.moises.expensetracker.db.DbSchema.*;
import br.com.borges.moises.expensetracker.db.ExpenseTrackerBaseHelper;
import br.com.borges.moises.expensetracker.model.Category;
import br.com.borges.moises.expensetracker.model.CategoryType;

/**
 * Created by moise on 30/12/2015.
 */
public class CategoryRepository {

    private static CategoryRepository sCategoryRepository;
    private SQLiteDatabase mDatabase;
    private static final String CATEGORY_ID = "cat_id";
    private static final String CATEGORY_DESCRIPTION = "cat_desc";
    private static final String CARTEGORY_TYPE = "cat_type";
    private static final String CATEGORY_TYPE_DESCRIPTION = "cat_type_desc";
    private static final String AS = " as ";
    private static final String COMMA_SEP = ",";
    private static final String COLUMNS = CategoryTable.Columns.ID_WITH_PREFIX + AS + CATEGORY_ID + COMMA_SEP +
            CategoryTable.Columns.DESCRIPTION_WITH_PREFIX + AS + CATEGORY_DESCRIPTION + COMMA_SEP +
            CategoryTable.Columns.TYPE_WITH_PREFIX + AS + CARTEGORY_TYPE + COMMA_SEP +
            CategoryTypeTable.Columns.DESCRIPTION_WITH_PREFIX + AS + CATEGORY_TYPE_DESCRIPTION;

    private String mQuerySingleCategory =
            "select " + COLUMNS + "  from " + CategoryTable.NAME + " inner join " + CategoryTypeTable.NAME +
            " on " + CategoryTable.Columns.TYPE_WITH_PREFIX + " = " + CategoryTypeTable.Columns.ID_WITH_PREFIX +
            " and " + CategoryTable.Columns.ID_WITH_PREFIX + " = ?;";

    private String mQueryAllCategories =
            "select " + COLUMNS + "  from " + CategoryTable.NAME + " inner join " + CategoryTypeTable.NAME +
                    " on " + CategoryTable.Columns.TYPE_WITH_PREFIX + " = " + CategoryTypeTable.Columns.ID_WITH_PREFIX + ";";

    private String mQueryCategoryTypes =
            "select " + COLUMNS + "  from " + CategoryTable.NAME + " inner join " + CategoryTypeTable.NAME +
                    " on " + CategoryTable.Columns.TYPE_WITH_PREFIX + " = " + CategoryTypeTable.Columns.ID_WITH_PREFIX +
                    " and " + CategoryTypeTable.Columns.ID_WITH_PREFIX + " = ?;";

    private CategoryRepository(Context context) {
        mDatabase = ExpenseTrackerBaseHelper.getBaseHelper(context)
                .getWritableDatabase();
    }

    public static CategoryRepository getCategoryRepository(Context context) {
        if (sCategoryRepository == null)
            sCategoryRepository = new CategoryRepository(context);

        return sCategoryRepository;
    }

    public void addCategory(Category category) {
        ContentValues contentValues = getContentValues(category);
        mDatabase.insert(CategoryTable.NAME, null,contentValues);
    }

    private ContentValues getContentValues(Category category) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoryTable.Columns.ID,category.getId());
        contentValues.put(CategoryTable.Columns.DESCRIPTION,category.getDescription());
        contentValues.put(CategoryTable.Columns.TYPE, category.getCategoryTypeId());
        return contentValues;
    }

    public void deleteCategory(Category category) {
        String whereClause = CategoryTable.Columns.ID + " = ?";
        String[] whereArgs = new String[] {String.valueOf(category.getId())};
        mDatabase.delete(CategoryTable.NAME, whereClause, whereArgs);
    }

    public void updateCategory(Category category) {
        ContentValues contentValues = getContentValues(category);
        String whereClause = CategoryTable.Columns.ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(category.getId())};
        mDatabase.update(CategoryTable.NAME, contentValues, whereClause, whereArgs);
    }

    public Category getCategory(int id) {
        String[] selectionArgs = new String[]{String.valueOf(id)};
        Cursor cursor = mDatabase.rawQuery(mQuerySingleCategory, selectionArgs);
        cursor.moveToFirst();
        Category category = getCategory(cursor);
        return category;
    }

    private Category getCategory(Cursor cursor) {
        Category category = new Category();
        category.setId(cursor.getInt(cursor.getColumnIndex(CATEGORY_ID)));
        category.setDescription(cursor.getString(cursor.getColumnIndex(CATEGORY_DESCRIPTION)));
        category.setCategoryTypeId(cursor.getInt(cursor.getColumnIndex(CATEGORY_TYPE_DESCRIPTION)));
        int typeId = cursor.getInt(cursor.getColumnIndex(CATEGORY_TYPE_DESCRIPTION));
        switch (typeId) {
            case CategoryTypeTable.Values.EXPENSE_ID:
                category.setCategoryType(CategoryType.EXPENSE);
                break;
            case CategoryTypeTable.Values.INCOME_ID:
                category.setCategoryType(CategoryType.INCOME);
                break;
            default:
                Log.d("CategoryType","Category with type_id " + typeId + " found");
        }
        return category;
    }

    private List<Category> getCategories(String rawQuery, String[] selectionArgs) {
        Cursor cursor = mDatabase.rawQuery(rawQuery, selectionArgs);
        List<Category> categories = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Category category = getCategory(cursor);
            categories.add(category);
            cursor.moveToNext();
        }
        return categories;
    }

    public List<Category> getCategories() {
        return getCategories(mQueryAllCategories, new String[]{});
    }

    public List<Category> getExpenseCategories() {
        return getCategories(mQueryCategoryTypes, new String[]{String.valueOf(CategoryTypeTable.Values.EXPENSE_ID)});
    }

    public List<Category> getIncomeCategories() {
        return getCategories(mQueryCategoryTypes, new String[]{String.valueOf(CategoryTypeTable.Values.INCOME_ID)});
    }
}
