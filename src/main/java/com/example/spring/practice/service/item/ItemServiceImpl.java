package com.example.spring.practice.service.item;

import com.example.spring.practice.domain.item.Item;
import com.example.spring.practice.domain.item.NewItem;
import com.example.spring.practice.domain.item.UpdateItem;
import com.example.spring.practice.service.FileStore;
import com.example.spring.practice.repository.Item.ItemRepository;
import com.example.spring.practice.service.UploadFile;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;

    private final FileStore fileStore;
    @Value("${file.dir}")
    private String fileDir;
    @Override
    public Item newItem(NewItem newItem) throws IOException {
        UploadFile uploadFile = fileStore.storeFile(newItem.getMultipartFile());
        List<UploadFile> uploadFiles = fileStore.storeFileList(newItem.getMultipartFileList());
        Item item = new Item(makeId(),newItem.getName(), newItem.getPrice(), newItem.getQuantity(), newItem.getInfo(),uploadFile,uploadFiles);
        Item saveItem = itemRepository.save(item);
        return saveItem;
    }
    @Override
    public List<Item> items() {
        return itemRepository.findAll();
    }
    @Override
    public Item item(String id) {
        return itemRepository.findById(id);
    }

    @Override
    public void updateItem(UpdateItem updateItem) {
        Item item = itemRepository.findById(updateItem.getId());
//        item.setUploadFile(updateItem.getUploadFile());
//        item.setUploadFileList(updateItem.getUploadFileList());
        item.setId(updateItem.getId());
        item.setName(updateItem.getName());
        item.setPrice(updateItem.getPrice());
        item.setQuantity(updateItem.getQuantity());
        item.setInfo(updateItem.getInfo());
        itemRepository.updateItem(item);
    }

    @Override
    public String getFile(String filename) {
        return fileStore.getFullPath(filename);
    }

    private String makeId(){
        return RandomStringUtils.random(10,false,true)+"_"+RandomStringUtils.random(3,true,false);
    }
}
