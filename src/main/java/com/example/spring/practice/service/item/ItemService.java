package com.example.spring.practice.service.item;

import com.example.spring.practice.domain.item.Item;
import com.example.spring.practice.domain.item.UpdateItem;

import java.util.List;

public interface ItemService {
    public Item newItem(Item item);

    public List<Item> items();

    public Item item(Long id);

    public void updateItem(UpdateItem updateItem);
}
