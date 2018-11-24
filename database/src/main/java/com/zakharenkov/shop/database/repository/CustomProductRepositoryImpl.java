package com.zakharenkov.shop.database.repository;

import com.zakharenkov.shop.database.dto.FilterDto;
import com.zakharenkov.shop.database.model.Category_;
import com.zakharenkov.shop.database.model.Product;
import com.zakharenkov.shop.database.model.ProductDetail_;
import com.zakharenkov.shop.database.model.Product_;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CustomProductRepositoryImpl implements CustomProductRepository {

    public static final String ANY = "-";

    @Autowired
    private EntityManager entityManager;

    @Override
    public Long getCountProduct(FilterDto filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        Root<Product> root = criteria.from(Product.class);

        List<Predicate> predicates = getPredicates(filter, cb, root);
        Predicate[] predicateArray = new Predicate[predicates.size()];
        predicates.toArray(predicateArray);

        criteria
                .select(cb.count(root))
                .where(predicateArray);

        return entityManager.createQuery(criteria).getSingleResult();
    }

    @Override
    public List<Product> findByFilter(FilterDto filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
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


        return entityManager.createQuery(criteria)
                .setFirstResult(filter.getPageSize() * (filter.getPage() - 1))
                .setMaxResults(filter.getPageSize())
                .getResultList();
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

    private Order getOrderBy(FilterDto filter, CriteriaBuilder cb, Root<Product> root) {
        Order orderBy;
        if (filter.isOrderByDesc()) {
            orderBy = cb.desc(root.get(Product_.price));
        } else {
            orderBy = cb.asc(root.get(Product_.price));
        }
        return orderBy;
    }
}
