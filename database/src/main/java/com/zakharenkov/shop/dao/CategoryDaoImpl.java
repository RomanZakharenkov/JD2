package com.zakharenkov.shop.dao;

import com.zakharenkov.shop.model.Category;

public class CategoryDaoImpl extends BaseDaoImpl<Integer, Category> implements CategoryDao {

    private static final CategoryDao INSTANCE = new CategoryDaoImpl(Category.class);

    public CategoryDaoImpl(Class<Category> clazz) {
        super(clazz);
    }

    public static CategoryDao getInstance() {
        return INSTANCE;
    }
}
