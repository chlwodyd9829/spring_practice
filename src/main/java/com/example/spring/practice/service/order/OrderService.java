package com.example.spring.practice.service.order;

import com.example.spring.practice.domain.item.Item;
import com.example.spring.practice.domain.member.Member;
import com.example.spring.practice.domain.order.Order;
import com.example.spring.practice.domain.order.OrderDetail;
import com.example.spring.practice.domain.order.OrderState;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface OrderService {
    public Order makeOrder(Member member, List<Item> item, Map<Item,Integer> cnt) throws ParseException;

    public void changeState(String orderId, OrderState orderState);

    public List<Order> orders();

    public List<OrderDetail> findOrderDetail(String orderId);

    public Order findOrder(String orderId);
}
