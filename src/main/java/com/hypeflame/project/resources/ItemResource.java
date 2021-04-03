package com.hypeflame.project.resources;

import com.hypeflame.project.domain.exception.DomainException;
import com.hypeflame.project.dto.ItemFullResponseModel;
import com.hypeflame.project.dto.ItemRequestModel;
import com.hypeflame.project.dto.ItemResponseModel;
import com.hypeflame.project.dto.ItemUptadeRequestModel;
import com.hypeflame.project.entities.Item;
import com.hypeflame.project.services.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        try {
            List<Item> itemList = itemService.findAll();
            return ResponseEntity.ok().body(toCollectionModel(itemList));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ItemFullResponseModel> findById(@PathVariable Long id){
         try {
             Item item = itemService.findById(id);
             return ResponseEntity.ok().body(toFullModel(item));
         }catch (Exception e) {
             return ResponseEntity.notFound().build();
         }
    }

    @PostMapping
    public ResponseEntity<ItemResponseModel> insertItem(@RequestBody ItemRequestModel itemRequestModel){
        try {
            Item item = toEntity(itemRequestModel);
            itemService.insertItem(item, itemRequestModel.getOrderId());
            return ResponseEntity.ok().body(toModel(item));
        }catch (Exception e){
            throw new DomainException("This order doesn't exist.");
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ItemResponseModel> updateItem(@PathVariable Long id, @RequestBody @Valid ItemUptadeRequestModel itemUptadeRequestModel){
        try {
            itemService.updateItem(id, itemUptadeRequestModel);
            Item item = itemService.findById(id);
            return ResponseEntity.ok().body(toModel(item));
        }catch (Exception e){
            throw new DomainException("This item Doesn't exist");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id){
        try {
            itemService.deleteItem(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
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
