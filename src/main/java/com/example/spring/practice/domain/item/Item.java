package com.example.spring.practice.domain.item;

import com.example.spring.practice.service.UploadFile;
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

    private UploadFile uploadFile;

    public Item(){}

    public Item(String name, int price, int quantity, String info,UploadFile uploadFile) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.info = info;
        this.uploadFile = uploadFile;
    }

    public Item(Long id, String name, int price, int quantity, String info, UploadFile uploadFile) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.info = info;
        this.uploadFile=uploadFile;
    }
}
