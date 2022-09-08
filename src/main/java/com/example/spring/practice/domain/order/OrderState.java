package com.example.spring.practice.domain.order;

public enum OrderState {

    READY("준비"), DELIVERY("배송 중"), COMPLETE("완료"),CANCEL("취소");

    private final String description;

    OrderState(String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}
