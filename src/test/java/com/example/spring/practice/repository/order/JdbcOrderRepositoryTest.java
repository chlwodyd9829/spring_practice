package com.example.spring.practice.repository.order;

import com.example.spring.practice.domain.order.Order;
import com.example.spring.practice.domain.order.OrderDetail;
import com.example.spring.practice.domain.order.OrderState;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.spring.practice.repository.connection.ConnectionConst.*;

class JdbcOrderRepositoryTest {

    private final OrderRepository orderRepository = new JdbcOrderRepository(new DriverManagerDataSource(URL,USERNAME,PASSWORD));
    @Test
    void save(){
        Order order = new Order("123", Timestamp.valueOf(LocalDateTime.now()).toString(), "asd", "qwewqe", OrderState.READY);
        OrderDetail orderDetail = new OrderDetail("qwe", order.getId(), "ASD", 10000, 5,null);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDetailList.add(orderDetail);
        orderRepository.save(order,orderDetailList);

        Order findOrder = orderRepository.findByOrder("123");
        Assertions.assertThat(findOrder.getId()).isEqualTo(order.getId());

    }

}