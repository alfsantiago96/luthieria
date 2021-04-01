package com.hypeflame.project.services;

import com.hypeflame.project.entities.Item;
import com.hypeflame.project.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findAll(){
        List<Item> itemList = itemRepository.findAll();
        return itemList;
    }

    public Item findById(Long id){
        Optional<Item> item = itemRepository.findById(id);
        return item.orElseThrow();
    }

}