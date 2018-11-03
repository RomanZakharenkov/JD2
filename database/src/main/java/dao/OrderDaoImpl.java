package dao;

import model.Order;

public class OrderDaoImpl extends BaseDaoImpl<Long, Order> implements OrderDao {

    private static final OrderDao INSTANCE = new OrderDaoImpl(Order.class);

    // TODO: реализация кастомных методов на заказы

    public OrderDaoImpl(Class<Order> clazz) {
        super(clazz);
    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }
}
