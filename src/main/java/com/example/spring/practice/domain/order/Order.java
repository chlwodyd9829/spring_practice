package com.example.spring.practice.domain.order;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Setter
@Getter
public class Order {
    private String id;
    private String orderDate;
    private String memberId;
    private String address;
    private OrderState orderState;

    public Order(){}

    public Order(String id, String orderDate, String memberId, String address, OrderState orderState) {
        this.id = id;
        this.orderDate = orderDate;
        this.memberId = memberId;
        this.address = address;
        this.orderState = orderState;
    }
}
