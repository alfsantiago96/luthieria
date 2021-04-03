package com.hypeflame.project.services;

import com.hypeflame.project.dto.ItemUptadeRequestModel;
import com.hypeflame.project.entities.Item;
import com.hypeflame.project.entities.Order;
import com.hypeflame.project.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private OrderService orderService;

    public List<Item> findAll() {
        List<Item> itemList = itemRepository.findAll();
        return itemList;
    }

    public Item findById(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.orElseThrow();
    }

    public void insertItem(Item item, Long idOrder) {
        save(item);
        orderService.insertItem(item, idOrder);
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public void deleteItem(Long id){
        try {
            Item item = findById(id);
            save(item);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public void updateItem(Long id, ItemUptadeRequestModel itemObj) {
        Item item = findById(id);
        if (item != null) {
                item.setServiceDescription(itemObj.getServiceDescription());
                item.setServicePrice(itemObj.getServicePrice());
                save(item);
            } else {
            throw new RuntimeException();
            }
        }
    }
