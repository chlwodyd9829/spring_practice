package com.example.spring.practice.service.order;

import com.example.spring.practice.domain.item.Item;
import com.example.spring.practice.domain.member.Member;
import com.example.spring.practice.domain.order.Order;
import com.example.spring.practice.domain.order.OrderDetail;
import com.example.spring.practice.domain.order.OrderState;
import com.example.spring.practice.repository.Item.ItemRepository;
import com.example.spring.practice.repository.order.OrderRepository;
import com.example.spring.practice.service.UploadFile;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
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
            orderDetail.setUploadFile(findUploadFile(item.getId()));
            orderDetailList.add(orderDetail);
        }
        orderRepository.save(order,orderDetailList);
        return order;
    }

    @Override
    public void changeState(String orderId,OrderState orderState) {
        Order order = orderRepository.findByOrder(orderId);
        if(order == null || order.getOrderState().equals(OrderState.CANCEL)){
            return;
        }
        order.setOrderState(orderState);
        orderRepository.update(order);
    }

    @Override
    public List<Order> orders() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderDetail> findOrderDetail(String orderId) {
        List<OrderDetail> orderDetailList = orderRepository.findByOrderId(orderId);
        return orderDetailList;
    }

    @Override
    public Order findOrder(String orderId) {
        return orderRepository.findByOrder(orderId);
    }

    private UploadFile findUploadFile(String itemId) {
        Item item = itemRepository.findById(itemId);
        if(item == null){
            return null;
        }
        return item.getUploadFile();
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
