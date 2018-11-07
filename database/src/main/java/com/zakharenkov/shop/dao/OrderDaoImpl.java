package com.zakharenkov.shop.dao;

import com.zakharenkov.shop.model.Order;

public class OrderDaoImpl extends BaseDaoImpl<Long, Order> implements OrderDao {

    private static final OrderDao INSTANCE = new OrderDaoImpl(Order.class);

    public OrderDaoImpl(Class<Order> clazz) {
        super(clazz);
    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }
}
