package com.hypeflame.project.services;

import com.hypeflame.project.dto.ClientRequestModel;
import com.hypeflame.project.dto.OrderRequestModel;
import com.hypeflame.project.entities.Client;
import com.hypeflame.project.entities.Order;
import com.hypeflame.project.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Order> findAll(){
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }

    public Order findById(Long id){
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow();
    }

    public Order insert(Order order){
        return orderRepository.save(order);
    }

    public void delete(Long id){
        orderRepository.deleteById(id);
    }

    public Order update(Long id, Order order){
        Optional<Order> entity = orderRepository.findById(id);
        updateData(order, entity);
        orderRepository.save(order);
        return order;
    }
    private void updateData(Order order, Optional entity) {
        modelMapper.map(order, entity);
    }

}
