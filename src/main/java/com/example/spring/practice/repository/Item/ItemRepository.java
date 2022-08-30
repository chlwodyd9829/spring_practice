package com.example.spring.practice.repository.Item;

import com.example.spring.practice.domain.item.Item;

import java.util.List;

public interface ItemRepository {
    public Item save(Item item);

    public Item findById(Long id);

    public List<Item> findAll();
}
