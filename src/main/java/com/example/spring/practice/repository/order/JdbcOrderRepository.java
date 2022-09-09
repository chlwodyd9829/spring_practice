package com.example.spring.practice.repository.order;

import com.example.spring.practice.domain.order.Order;
import com.example.spring.practice.domain.order.OrderDetail;
import com.example.spring.practice.domain.order.OrderState;
import lombok.RequiredArgsConstructor;
import org.attoparser.trace.MarkupTraceEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcOrderRepository implements OrderRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Order order, List<OrderDetail> orderDetailList) {
        String sql = "insert into ordervo values(?,?,?,?,?)";
        jdbcTemplate.update(sql,order.getId(),order.getOrderDate(),order.getMemberId(),order.getAddress(),order.getOrderState().toString());

        sql = "insert into orderDetail values(?,?,?,?,?)";
        for (OrderDetail orderDetail : orderDetailList) {
            jdbcTemplate.update(sql,orderDetail.getId(),orderDetail.getOrderId(),orderDetail.getItemId(),orderDetail.getItemPrice(),orderDetail.getItemQuantity());
        }
    }

    @Override
    public Order findByOrder(String orderId) {
        String sql = "select * from ordervo where id = ?";
        return jdbcTemplate.query(sql, orderRowMapper(),orderId).stream().findAny().orElse(null);
    }

    @Override
    public List<OrderDetail> findByOrderId(String orderId) {
        String sql = "select * from orderDetail where orderId = ?";
        List<OrderDetail> detailList = jdbcTemplate.query(sql, orderDetailRowMapper(), orderId);
        return detailList;
    }

    @Override
    public List<Order> findByMemberId(String memberId) {
        String sql  = "select * from ordervo where = ?";
        List<Order> orderList = jdbcTemplate.query(sql, orderRowMapper(), memberId);
        return orderList;
    }

    @Override
    public List<Order> findAll() {
        String sql = "select * from ordervo";
        return jdbcTemplate.query(sql,orderRowMapper());
    }

    @Override
    public void update(Order order) {
        String sql = "update ordervo set orderState=? where id = ?";
        jdbcTemplate.update(sql, order.getOrderState().toString(),order.getId());
    }

    private RowMapper<OrderDetail> orderDetailRowMapper(){
        return (rs,rowNum)->{
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(rs.getString("id"));
            orderDetail.setOrderId(rs.getString("orderId"));
            orderDetail.setItemId(rs.getLong("itemId"));
            orderDetail.setItemPrice(rs.getInt("itemPrice"));
            orderDetail.setItemQuantity(rs.getInt("itemQuantity"));
            return orderDetail;
        };
    }
    private RowMapper<Order> orderRowMapper(){
        return (rs, rowNum) ->{
            Order order = new Order();
            order.setId(rs.getString("id"));
            order.setOrderDate(rs.getString("orderDate"));
            order.setMemberId(rs.getString("memberId"));
            order.setAddress(rs.getString("address"));
            order.setOrderState(OrderState.valueOf(rs.getString("orderState")));
            return order;
        };
    }
}
