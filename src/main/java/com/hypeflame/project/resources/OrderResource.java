package com.hypeflame.project.resources;

import com.hypeflame.project.dto.*;
import com.hypeflame.project.entities.Client;
import com.hypeflame.project.entities.Order;
import com.hypeflame.project.entities.Payment;
import com.hypeflame.project.entities.enums.OrderStatus;
import com.hypeflame.project.services.ClientService;
import com.hypeflame.project.services.OrderService;
import com.hypeflame.project.services.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ClientResource clientResource;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ClientService clientService;


    @GetMapping(value = "/{id}/pay")
    public ResponseEntity<OrderPaydResponseModel> payOrder(@PathVariable Long id){
        orderService.payOrder(id);
        Order order = orderService.findById(id);
        return ResponseEntity.ok().body(toOrderPaydModel(order));
    }

    @GetMapping(value = "/{id}/finish")
    public ResponseEntity<OrderResponseModel> finishOrder(@PathVariable Long id) {
        Order order = orderService.finishOrder(id);
        return ResponseEntity.ok().body(toModel(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseModel>> findAll() {
        List<Order> orderList = orderService.findAll();
        return ResponseEntity.ok().body(toCollectionModel(orderList));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderItemListResponseModel> findById(@PathVariable Long id){
        Order order = orderService.findById(id);
        return ResponseEntity.ok().body(toOrderItemListModel(order));
    }

    @GetMapping(value = "/full/{id}")
    public ResponseEntity<OrderFullResponseModel> findByIdFull(@PathVariable Long id){
        Order order = orderService.findById(id);
        return ResponseEntity.ok().body(toFullModel(order));
    }

    @PostMapping
    public ResponseEntity<OrderResponseModel> insert(@RequestBody OrderRequestModel obj){
        Client client = clientService.findById(obj.getClient());
        Order order = toEntity(obj);
        orderService.newOrder(order, client);
        return ResponseEntity.status(HttpStatus.CREATED).body(toModel(order));
    }

    //TODO
    @PostMapping(value = "/{id}/removeitem")
    public ResponseEntity<OrderItemListResponseModel> removeItem(@RequestBody OrderItemRemoveRequestModel orderItemRemoveRequestModel, @PathVariable Long id){
        Long orderId = id;
        Long itemId = orderItemRemoveRequestModel.getItemId();
        orderService.removeItem(orderId, itemId);
        Order order = orderService.findById(orderId);
        return ResponseEntity.ok().body(toOrderItemListModel(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //ModelMapper
    public OrderResponseModel toModel(Order order) {
        return modelMapper.map(order, OrderResponseModel.class);
    }
    public OrderItemListResponseModel toOrderItemListModel(Order order) {
        return modelMapper.map(order, OrderItemListResponseModel.class);
    }
    public OrderPaydResponseModel toOrderPaydModel(Order order){
        return modelMapper.map(order, OrderPaydResponseModel.class);
    }
    public OrderFullResponseModel toFullModel(Order order){
        return modelMapper.map(order, OrderFullResponseModel.class);
    }
    public Order toEntity(OrderRequestModel orderRequestModel){
        return modelMapper.map(orderRequestModel, Order.class);
    }
    public List<OrderResponseModel> toCollectionModel(List<Order> orderList) {
               return orderList.stream()
                        .map(order -> toModel(order))
                        .collect(Collectors.toList());
    }
}
