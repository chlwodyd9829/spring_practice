package com.example.spring.practice.domain.order;

import com.example.spring.practice.domain.item.Item;
import com.example.spring.practice.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class Order {
    private Long id;
    private String memberId;
    private String memberName;
    private Map<Long,String> ItemName;
    private Map<Long,Integer> ItemPrice;
    private Map<Long,Integer> ItemQuantity;
    private OrderStatus orderStatus;

    public Order(){}
    public Order(String memberId, String memberName, Map<Long, String> itemName, Map<Long, Integer> itemPrice, Map<Long, Integer> itemQuantity, OrderStatus orderStatus) {
        this.memberId = memberId;
        this.memberName = memberName;
        ItemName = itemName;
        ItemPrice = itemPrice;
        ItemQuantity = itemQuantity;
        this.orderStatus = orderStatus;
    }
}
