package com.example.spring.practice.service.item;

import com.example.spring.practice.domain.item.Item;
import com.example.spring.practice.repository.Item.ItemRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    public final ItemRepository itemRepository;
    @Override
    public Item newItem(Item item) {
        Item saveItem = itemRepository.save(item);
        return saveItem;
    }

    @Override
    public List<Item> items() {
        return itemRepository.findAll();
    }
}
