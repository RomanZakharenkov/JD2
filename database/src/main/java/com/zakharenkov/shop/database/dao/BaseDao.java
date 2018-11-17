package com.zakharenkov.shop.database.dao;

import com.zakharenkov.shop.database.model.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseDao<P extends Serializable, E extends BaseEntity<P>> {

    P save(E entity);

    void update(E entity);

    void delete(E entity);

    Optional<E> findById(P id);

    List<E> findAll();
}
