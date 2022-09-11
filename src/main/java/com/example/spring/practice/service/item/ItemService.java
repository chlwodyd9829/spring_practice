package com.example.spring.practice.service.item;

import com.example.spring.practice.domain.item.Item;
import com.example.spring.practice.domain.item.NewItem;
import com.example.spring.practice.domain.item.UpdateItem;

import java.io.IOException;
import java.util.List;

public interface ItemService {
    public Item newItem(NewItem newItem) throws IOException;

    public List<Item> items();

    public Item item(String id);

    public void updateItem(UpdateItem updateItem);

    public String getFile(String filename);
}
