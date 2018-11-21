package com.zakharenkov.shop.database.dao;

import com.zakharenkov.shop.database.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends BaseDaoImpl<Long, Order> implements OrderDao {

}
