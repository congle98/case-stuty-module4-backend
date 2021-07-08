package com.app.service.orderservice;

import com.app.entity.Order;
import com.app.entity.User;
import com.app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderService implements IOrderService{
    @Autowired
    OrderRepository orderRepository;
    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void remove(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Iterable<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }
}
