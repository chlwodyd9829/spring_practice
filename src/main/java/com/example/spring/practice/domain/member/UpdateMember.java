package com.example.spring.practice.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateMember {
    @NotEmpty
    private String id;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    private String address;
    @NotNull
    private Classification classification;

    public UpdateMember(){}
    public UpdateMember(String id, String password, String name, String address, Classification classification) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
        this.classification = classification;
    }
}
