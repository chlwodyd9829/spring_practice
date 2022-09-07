package com.example.spring.practice.domain.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetail {

    private String id;
    private String orderId;
    private Long itemId;
    private int itemPrice;
    private int itemQuantity;

    public OrderDetail(){}
    public OrderDetail(String id, String orderId, Long itemId, int itemPrice, int itemQuantity) {
        this.id = id;
        this.orderId = orderId;
        this.itemId = itemId;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }
}
