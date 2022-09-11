package com.example.spring.practice.domain.item;

import com.example.spring.practice.service.UploadFile;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Item {

    private String id;
    private String name;
    private int price;
    private int quantity;
    private String info;

    private UploadFile uploadFile;

    private List<UploadFile> uploadFileList;
    public Item(){}

    public Item(String name, int price, int quantity, String info,UploadFile uploadFile,List<UploadFile> uploadFiles) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.info = info;
        this.uploadFile = uploadFile;
        this.uploadFileList = uploadFiles;
    }

    public Item(String id, String name, int price, int quantity, String info, UploadFile uploadFile,List<UploadFile> uploadFiles) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.info = info;
        this.uploadFile=uploadFile;
        this.uploadFileList = uploadFiles;
    }
}
