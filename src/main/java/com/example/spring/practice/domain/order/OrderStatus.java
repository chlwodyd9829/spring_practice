package com.example.spring.practice.domain.order;

public enum OrderStatus {

    READY("준비"), DELIVERY("배송 중"), COMPLETE("완료");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
