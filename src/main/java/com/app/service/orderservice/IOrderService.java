package com.app.service.orderservice;

import com.app.entity.Order;
import com.app.entity.User;
import com.app.service.IGeneralService;

public interface IOrderService extends IGeneralService<Order> {
    Iterable<Order> findAllByUser(User user);
}
