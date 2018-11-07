package com.zakharenkov.shop.dao;

import lombok.Cleanup;
import com.zakharenkov.shop.model.BaseEntity;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

import static com.zakharenkov.shop.connection.ConnectionManager.getSession;

public abstract class BaseDaoImpl<P extends Serializable, E extends BaseEntity<P>> implements BaseDao<P, E> {

    private Class<E> clazz;

    public BaseDaoImpl(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    @SuppressWarnings("unchecked")
    public P save(E entity) {
        @Cleanup Session session = getSession();
        return (P) session.save(entity);
    }

    @Override
    public void update(E entity) {
        @Cleanup Session session = getSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
    }

    @Override
    public void delete(E entity) {
        @Cleanup Session session = getSession();
        session.beginTransaction();
        session.delete(entity);
        session.flush();
        session.getTransaction().commit();
    }

    @Override
    public E findById(P id) {
        @Cleanup Session session = getSession();
        return session.find(clazz, id);
    }

    @Override
    public List<E> findAll() {
        @Cleanup Session session = getSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<E> criteria = cb.createQuery(clazz);
        Root<E> root = criteria.from(clazz);

        criteria.select(root);

        return session.createQuery(criteria).list();
    }
}
