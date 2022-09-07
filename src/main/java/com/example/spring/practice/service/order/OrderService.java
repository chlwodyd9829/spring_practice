package com.example.spring.practice.service.order;

import com.example.spring.practice.domain.item.Item;
import com.example.spring.practice.domain.member.Member;
import com.example.spring.practice.domain.order.Order;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface OrderService {
    public Order makeOrder(Member member, List<Item> item, Map<Item,Integer> cnt) throws ParseException;
}
