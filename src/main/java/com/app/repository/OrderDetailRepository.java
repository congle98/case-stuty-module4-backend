package com.app.repository;

import com.app.entity.Order;
import com.app.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    Iterable<OrderDetail> findAllByOrder(Order order);
}
