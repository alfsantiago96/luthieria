package com.hypeflame.project.resources;

import com.hypeflame.project.dto.ItemFullResponseModel;
import com.hypeflame.project.dto.ItemRequestModel;
import com.hypeflame.project.dto.ItemResponseModel;
import com.hypeflame.project.entities.Item;
import com.hypeflame.project.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/items")
public class ItemResource {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ItemResponseModel>> findAll(){
        List<Item> itemList = itemService.findAll();
        return ResponseEntity.ok().body(toCollectionModel(itemList));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ItemFullResponseModel> findById(@PathVariable Long id){
         Item item = itemService.findById(id);
         return ResponseEntity.ok().body(toFullModel(item));
    }

    @PostMapping
    public ResponseEntity<ItemResponseModel> insertItem(@RequestBody ItemRequestModel itemRequestModel){
        Item item = toEntity(itemRequestModel);
        itemService.insertItem(item, itemRequestModel.getOrderId());
        return ResponseEntity.ok().body(toModel(item));
    }

    //ModelMapper
    public Item toEntity(ItemRequestModel itemRequestModel){
        return modelMapper.map(itemRequestModel, Item.class);
    }
    public ItemResponseModel toModel(Item item){
        return modelMapper.map(item, ItemResponseModel.class);
    }
    public ItemFullResponseModel toFullModel(Item item){
        return modelMapper.map(item, ItemFullResponseModel.class);
    }
    public List<ItemResponseModel> toCollectionModel(List<Item> itemList){
       return itemList.stream()
                .map(item -> toModel(item))
                .collect(Collectors.toList());
    }

}
