package dao;

import model.Category;

public class CategoryDaoImpl extends BaseDaoImpl<Integer, Category> implements CategoryDao {

    private static final CategoryDao INSTANCE = new CategoryDaoImpl(Category.class);

    // TODO: реализация кастомных методов на категории

    public CategoryDaoImpl(Class<Category> clazz) {
        super(clazz);
    }

    public static CategoryDao getInstance() {
        return INSTANCE;
    }
}
