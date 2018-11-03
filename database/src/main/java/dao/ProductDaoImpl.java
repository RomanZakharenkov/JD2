package dao;

import model.Product;

public class ProductDaoImpl extends BaseDaoImpl<Long, Product> implements ProductDao {

    private  static final ProductDao INSTANCE = new ProductDaoImpl(Product.class);

    // TODO: реализация кастомных методов на продукты

    public ProductDaoImpl(Class<Product> clazz) {
        super(clazz);
    }

    public static ProductDao getInstance() {
        return INSTANCE;
    }
}
