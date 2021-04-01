package com.hypeflame.project.resources;

import com.hypeflame.project.dto.ItemFullResponseModel;
import com.hypeflame.project.dto.ItemResponseModel;
import com.hypeflame.project.entities.Item;
import com.hypeflame.project.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    //ModelMapper
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
