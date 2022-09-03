package com.example.spring.practice.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateItem {
    @NotNull
    private Long id;
    @NotEmpty
    private String name;
    @NotNull
    private int price;
    @NotNull
    private int quantity;

    private String info;

    public UpdateItem(){
    }

    public UpdateItem(Long id, String name, int price, int quantity, String info) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.info = info;
    }
}
