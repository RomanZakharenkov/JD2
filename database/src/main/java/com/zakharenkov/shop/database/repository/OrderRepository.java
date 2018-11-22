package com.zakharenkov.shop.database.repository;

import com.zakharenkov.shop.database.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
