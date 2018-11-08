package com.zakharenkov.shop.dao;

import com.querydsl.jpa.impl.JPAQuery;
import com.zakharenkov.shop.dto.FilterDto;
import lombok.Cleanup;
import com.zakharenkov.shop.model.Category_;
import com.zakharenkov.shop.model.Product;
import com.zakharenkov.shop.model.ProductDetail_;
import com.zakharenkov.shop.model.Product_;
import com.zakharenkov.shop.model.QProduct;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.zakharenkov.shop.connection.ConnectionManager.getSession;

public class ProductDaoImpl extends BaseDaoImpl<Long, Product> implements ProductDao {

    private static final ProductDao INSTANCE = new ProductDaoImpl();
    private static final String ANY = "Любой";
    private static final String ASC = "asc";

    private ProductDaoImpl() {
        super(Product.class);
    }

    public Long getCountProduct(FilterDto filter) {
        @Cleanup Session session = getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        Root<Product> root = criteria.from(Product.class);

        List<Predicate> predicates = getPredicates(filter, cb, root);
        Predicate[] predicateArray = new Predicate[predicates.size()];
        predicates.toArray(predicateArray);

        criteria
                .select(cb.count(root))
                .where(predicateArray);

        return session.createQuery(criteria).getSingleResult();
    }

    @Override
    public List<Product> findByFilter(FilterDto filter) {
        @Cleanup Session session = getSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteria = cb.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);

        List<Predicate> predicates = getPredicates(filter, cb, root);
        Predicate[] predicateArray = new Predicate[predicates.size()];
        predicates.toArray(predicateArray);
        Order orderBy = getOrderBy(filter, cb, root);

        criteria
                .select(root)
                .where(predicateArray)
                .orderBy(orderBy);

        return session.createQuery(criteria)
                .setFirstResult(filter.getPageSize() * (filter.getPage() - 1))
                .setMaxResults(filter.getPageSize() * filter.getPage())
                .list();
    }

    @Override
    public Set<String> getAllBrand() {
        @Cleanup Session session = getSession();

        Set<String> allBrand = new HashSet<>();
        allBrand.add("Любой");
        List<String> fetch = new JPAQuery<Product>(session)
                .select(QProduct.product.productDetail.brand)
                .from(QProduct.product)
                .fetch();
        allBrand.addAll(fetch);
        return allBrand;
    }

    private Order getOrderBy(FilterDto filter, CriteriaBuilder cb, Root<Product> root) {
        Order orderBy;
        if (ASC.equals(filter.getOrderBy())) {
            orderBy = cb.asc(root.get(Product_.price));
        } else {
            orderBy = cb.desc(root.get(Product_.price));
        }
        return orderBy;
    }

    private List<Predicate> getPredicates(FilterDto filter, CriteriaBuilder cb, Root<Product> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getMinPrice() != null) {
            predicates.add(cb.ge(root.get(Product_.price), filter.getMinPrice()));
        }
        if (filter.getMaxPrice() != null) {
            predicates.add(cb.le(root.get(Product_.price), filter.getMaxPrice()));
        }

        if (filter.getBrand() != null && !ANY.equals(filter.getBrand())) {
            predicates.add(cb.equal(root.get(Product_.productDetail).get(ProductDetail_.brand), filter.getBrand()));
        }

        if (filter.getTv() != null && filter.getAudio() != null) {
            predicates.add(cb.or(
                    cb.equal(root.get(Product_.category).get(Category_.id), filter.getTv()),
                    cb.equal(root.get(Product_.category).get(Category_.id), filter.getAudio())));
        } else {
            if (filter.getTv() != null) {
                predicates.add(cb.equal(root.get(Product_.category).get(Category_.id), filter.getTv()));
            }
            if (filter.getAudio() != null) {
                predicates.add(cb.equal(root.get(Product_.category).get(Category_.id), filter.getAudio()));
            }
        }

        return predicates;
    }

    public static ProductDao getInstance() {
        return INSTANCE;
    }
}
