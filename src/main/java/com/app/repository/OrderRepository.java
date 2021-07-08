package com.app.repository;

import com.app.entity.Order;
import com.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Iterable<Order> findAllByUser(User user);
}
