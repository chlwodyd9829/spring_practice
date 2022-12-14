package com.example.spring.practice.service.order;

import com.example.spring.practice.domain.item.Item;
import com.example.spring.practice.domain.member.Classification;
import com.example.spring.practice.domain.member.Member;
import com.example.spring.practice.domain.order.Order;
import com.example.spring.practice.domain.order.OrderState;
import com.example.spring.practice.repository.Item.ItemRepository;
import com.example.spring.practice.repository.Item.JdbcItemRepository;
import com.example.spring.practice.repository.order.JdbcOrderRepository;
import com.example.spring.practice.repository.order.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;

import static com.example.spring.practice.repository.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class OrderServiceImplTest {
    OrderRepository orderRepository = new JdbcOrderRepository(new DriverManagerDataSource(URL,USERNAME,PASSWORD));
    ItemRepository itemRepository = new JdbcItemRepository(new DriverManagerDataSource(URL, USERNAME, PASSWORD));
    OrderService orderService = new OrderServiceImpl(orderRepository,itemRepository);
    @Test
    void makeOrder() throws ParseException {
        Member member = new Member("zxc", "111", "choi", "Home", Classification.USER);
        Item item1 = new Item("4040750653_aHs","곰 인형", 5000, 5, "인형", null,null);
        Item item2 = new Item("5007076965_EXq","컵", 500, 1000, "종이컵", null,null);

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
    void changeState(){
        Order asd = new Order("111", LocalDateTime.now().toString(), "asd", "123", OrderState.READY);
        String orderId = orderRepository.findAll().stream().filter(o->o.getId().equals("111")).findFirst().orElse(null).getId();
        Order order = orderRepository.findByOrder(orderId);
        orderService.changeState(order.getId(), OrderState.DELIVERY);

        Order findOrder = orderRepository.findByOrder(orderId);

        assertThat(order.getId()).isEqualTo(findOrder.getId());
        assertThat(order.getOrderDate()).isEqualTo(findOrder.getOrderDate());
        assertThat(order.getMemberId()).isEqualTo(findOrder.getMemberId());
    }
}