package com.example.spring.practice.service.item;

import com.example.spring.practice.domain.item.Item;
import com.example.spring.practice.domain.item.UpdateItem;
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
    @Override
    public Item item(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public void updateItem(UpdateItem updateItem) {
        Item item = itemRepository.findById(updateItem.getId());
        item.setName(updateItem.getName());
        item.setPrice(updateItem.getPrice());
        item.setQuantity(updateItem.getQuantity());
        item.setInfo(updateItem.getInfo());
        itemRepository.updateItem(item);
    }
}
