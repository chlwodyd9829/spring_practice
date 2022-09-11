package com.example.spring.practice.domain.item;

import com.example.spring.practice.service.UploadFile;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class UpdateItem {
    @NotNull
    private String uploadFile;
    @NotNull
    private List<String> uploadFileList;
    @NotNull
    private String id;
    @NotEmpty(message = "이름을 입력하세요.")
    private String name;
    @NotNull(message = "가격을 입력하세요.")
    private int price;
    @NotNull(message = "수량을 입력하세요")
    private int quantity;

    private String info;

    public UpdateItem(){
    }

    public UpdateItem(String uploadFile,List<String> uploadFiles,String id, String name, int price, int quantity, String info) {
        this.uploadFile = uploadFile;
        this.uploadFileList=uploadFiles;
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.info = info;
    }
}
