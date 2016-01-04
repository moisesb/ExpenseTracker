package br.com.borges.moises.expensetracker.model;

/**
 * Created by moise on 30/12/2015.
 */
public class Category {
    private int mId;
    private String mDescription;
    private int mCategoryTypeId;
    private CategoryType mCategoryType;

    public Category() {
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getCategoryTypeId() {
        return mCategoryTypeId;
    }

    public void setCategoryTypeId(int categoryTypeId) {
        mCategoryTypeId = categoryTypeId;
    }

    public CategoryType getCategoryType() {
        return mCategoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        mCategoryType = categoryType;
    }
}
