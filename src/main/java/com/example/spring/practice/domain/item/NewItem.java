package com.example.spring.practice.domain.item;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NewItem {
    @NotEmpty
    private String name;
    @NotNull
    @Range(min=0,max=9999999)
    private int price;
    @NotNull
    @Range(min=0,max=999999)
    private int quantity;

    private String info;

    private MultipartFile multipartFile;
}
