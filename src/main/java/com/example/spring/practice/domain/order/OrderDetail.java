package com.example.spring.practice.domain.order;

import com.example.spring.practice.service.UploadFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetail {

    private String id;
    private String orderId;
    private String itemId;
    private int itemPrice;
    private int itemQuantity;

    private UploadFile uploadFile;

    public OrderDetail(){}
    public OrderDetail(String id, String orderId, String itemId, int itemPrice, int itemQuantity,UploadFile uploadFile) {
        this.id = id;
        this.orderId = orderId;
        this.itemId = itemId;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.uploadFile = uploadFile;
    }
}
