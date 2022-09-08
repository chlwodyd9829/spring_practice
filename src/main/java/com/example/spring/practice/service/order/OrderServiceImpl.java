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

    @Override
    public void cancelOrder(String orderId) {
        Order order = orderRepository.findByOrder(orderId);
        if(order == null || order.getOrderState().equals(OrderState.CANCEL)){
            return;
        }
        order.setOrderState(OrderState.CANCEL);
        orderRepository.update(order,OrderState.CANCEL);
    }

    private String makeOrderDetailId(){
        return UUID.randomUUID().toString();
    }
    private String makeOrderId(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String orderId = simpleDateFormat.format(calendar.getTime());
        return orderId + "_" + RandomStringUtils.random(5,false,true);
    }
    private String makeDate() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String format = simpleDateFormat.format(calendar.getTime());
        return format;
    }
}
