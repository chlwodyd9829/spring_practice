package com.example.spring.practice.domain.member;

public enum Classification {
    USER("회원"),ADMIN("관리자");
    private final String Description;

    Classification(String description) {
        Description = description;
    }

    public String getDescription() {
        return Description;
    }
}
