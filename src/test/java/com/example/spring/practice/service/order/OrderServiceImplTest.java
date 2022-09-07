package com.example.spring.practice.service.order;

import com.example.spring.practice.domain.item.Item;
import com.example.spring.practice.domain.member.Classification;
import com.example.spring.practice.domain.member.Member;
import com.example.spring.practice.domain.order.Order;
import com.example.spring.practice.repository.order.JdbcOrderRepository;
import com.example.spring.practice.repository.order.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.spring.practice.repository.connection.ConnectionConst.*;
import static org.junit.jupiter.api.Assertions.*;


class OrderServiceImplTest {
    OrderService orderService = new OrderServiceImpl(new JdbcOrderRepository(new DriverManagerDataSource(URL,USERNAME,PASSWORD)));
    @Test
    void makeOrder() throws ParseException {
        Member member = new Member("asd", "123", "spring", "asdsa", Classification.USER);
        Item item = new Item(1L, "qwe", 5000, 50, "hi", null);

        List<Item> items = new ArrayList();
        items.add(item);
        Map<Item, Integer> itemMap = new HashMap<>();

        itemMap.put(item,5);
        Order order = orderService.makeOrder(member, items, itemMap);

        System.out.println("order = " + order);
    }
}