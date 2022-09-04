package com.example.spring.practice.repository.Item;

import com.example.spring.practice.domain.item.Item;

import java.util.List;
import java.util.Optional;


public interface ItemRepository {
    public Item save(Item item);

    public Optional<Item> findById(Long id);

    public List<Item> findAll();

    public void updateItem(Item item);
}
