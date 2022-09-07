package com.example.spring.practice.service.order;

import com.example.spring.practice.domain.item.Item;
import com.example.spring.practice.domain.member.Member;
import com.example.spring.practice.domain.order.Order;
import com.example.spring.practice.domain.order.OrderDetail;
import com.example.spring.practice.domain.order.OrderState;
import com.example.spring.practice.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.spi.CalendarNameProvider;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    @Override
    public Order makeOrder(Member member, List<Item> items, Map<Item,Integer> cnt) throws ParseException {
        Order order = new Order();
        String orderId = makeOrderId();
        order.setId(orderId);
        order.setOrderDate(makeDate());
        order.setMemberId(member.getId());
        order.setAddress(member.getAddress());
        order.setOrderState(OrderState.READY);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (Item item : items) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setId(makeOrderDetailId());
            orderDetail.setItemId(item.getId());
            orderDetail.setItemPrice(item.getPrice());
            orderDetail.setItemQuantity(cnt.get(item));
            orderDetailList.add(orderDetail);
        }
        orderRepository.save(order,orderDetailList);
        return order;
    }
    private String makeOrderDetailId(){
        return UUID.randomUUID().toString();
    }
    private String makeOrderId(){
        String orderId = "";
        orderId = date("yyyyMMddHHmmss");
        return orderId + "_" + RandomStringUtils.random(5);
    }
    private Date makeDate() throws ParseException {
        String date = date("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(date);
    }
    private String date(String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Calendar dateTime = Calendar.getInstance();
        return simpleDateFormat.format(dateTime);
    }
}
