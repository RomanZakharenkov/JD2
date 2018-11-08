package com.zakharenkov.shop.dao;

import com.zakharenkov.shop.model.Category;

public class CategoryDaoImpl extends BaseDaoImpl<Integer, Category> implements CategoryDao {

    private static final CategoryDao INSTANCE = new CategoryDaoImpl();

    private CategoryDaoImpl() {
        super(Category.class);
    }

    public static CategoryDao getInstance() {
        return INSTANCE;
    }
}
