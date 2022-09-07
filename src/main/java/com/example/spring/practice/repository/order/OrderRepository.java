package com.example.spring.practice.repository.order;

import com.example.spring.practice.domain.order.Order;
import com.example.spring.practice.domain.order.OrderDetail;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    public void save(Order order, List<OrderDetail> orderDetailList);

    public Optional<Order> findByOrder(String orderId);
    public List<OrderDetail> findByOrderId(String orderId);

    public List<Order> findByMemberId(String memberId);

    public List<Order> findAll();
}
