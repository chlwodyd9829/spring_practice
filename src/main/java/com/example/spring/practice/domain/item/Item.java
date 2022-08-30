package com.example.spring.practice.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private Long id;
    private String name;
    private int price;
    private int quantity;
    private String info;


    public Item(){}

    public Item(String name, int price, int quantity, String info) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.info = info;
    }
}
