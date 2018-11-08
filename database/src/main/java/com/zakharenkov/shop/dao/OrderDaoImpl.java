package com.zakharenkov.shop.dao;

import com.zakharenkov.shop.model.Order;

public class OrderDaoImpl extends BaseDaoImpl<Long, Order> implements OrderDao {

    private static final OrderDao INSTANCE = new OrderDaoImpl();

    private OrderDaoImpl() {
        super(Order.class);
    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }
}
