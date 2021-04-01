package com.hypeflame.project.resources;

import com.hypeflame.project.dto.OrderFullResponseModel;
import com.hypeflame.project.dto.OrderItemListResponseModel;
import com.hypeflame.project.dto.OrderRequestModel;
import com.hypeflame.project.dto.OrderResponseModel;
import com.hypeflame.project.entities.Order;
import com.hypeflame.project.entities.Payment;
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

    @GetMapping(value = "/pay/{orderId}")
    public ResponseEntity<Payment> payRequest(@PathVariable Long orderId){
        orderService.payOrder(orderId);
        Payment payment = paymentService.findById(orderId);
        return ResponseEntity.ok().body(payment);
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
    public ResponseEntity<OrderResponseModel> insert(@RequestBody OrderRequestModel orderRequestModel){
        Order order = toEntity(orderRequestModel);
        orderService.insert(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(toModel(order));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order order){
        Order entity = orderService.update(id, order);
        return ResponseEntity.ok().body(entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
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
