package com.app.service.orderdetailservice;

import com.app.entity.Order;
import com.app.entity.OrderDetail;
import com.app.service.IGeneralService;

public interface IOrderDetailService extends IGeneralService<OrderDetail> {
    Iterable<OrderDetail> findAllByOrder(Order order);
}
