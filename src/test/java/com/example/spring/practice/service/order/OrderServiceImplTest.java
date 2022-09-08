package com.example.spring.practice.service.order;

import com.example.spring.practice.domain.item.Item;
import com.example.spring.practice.domain.member.Classification;
import com.example.spring.practice.domain.member.Member;
import com.example.spring.practice.domain.order.Order;
import com.example.spring.practice.repository.order.JdbcOrderRepository;
import com.example.spring.practice.repository.order.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.text.ParseException;
import java.util.*;

import static com.example.spring.practice.repository.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class OrderServiceImplTest {
    OrderRepository orderRepository = new JdbcOrderRepository(new DriverManagerDataSource(URL,USERNAME,PASSWORD));
    OrderService orderService = new OrderServiceImpl(orderRepository);
    @Test
    void makeOrder() throws ParseException {
        Member member = new Member("asd", "123", "spring", "asdsa", Classification.USER);
        Item item1 = new Item(1L, "qwe", 5000, 50, "hi", null);
        Item item2 = new Item(2L, "spring", 1000, 50, "hello", null);

        List<Item> items = new ArrayList();
        items.add(item1);
        items.add(item2);
        Map<Item, Integer> itemMap = new HashMap<>();

        itemMap.put(item1,5);
        itemMap.put(item2,10);
        Order order = orderService.makeOrder(member, items, itemMap);
        String orderId = order.getId();

        Order findOrder = orderRepository.findByOrder(orderId);

        System.out.println("findOrder.getOrderDate() = " + findOrder.getOrderDate());
        assertThat(order.getId()).isEqualTo(findOrder.getId());
        assertThat(order.getOrderDate()).isEqualTo(findOrder.getOrderDate());
        assertThat(order.getMemberId()).isEqualTo(findOrder.getMemberId());
    }

    @Test
    void orderCancel(){
        String orderId = orderRepository.findAll().stream().findAny().get().getId();
        Order order = orderRepository.findByOrder(orderId);
        orderService.cancelOrder(order.getId());

        Order findOrder = orderRepository.findByOrder(orderId);

        assertThat(order.getId()).isEqualTo(findOrder.getId());
        assertThat(order.getOrderDate()).isEqualTo(findOrder.getOrderDate());
        assertThat(order.getMemberId()).isEqualTo(findOrder.getMemberId());
    }
}