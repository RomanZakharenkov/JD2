package service;

import dao.ProductDaoImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Product;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductService {

    private static final ProductService INSTANCE = new ProductService();

    public List<Product> findAll() {
        return ProductDaoImpl.getInstance().findAll();
    }

    public static ProductService getInstance() {
        return INSTANCE;
    }
}
