package com.hypeflame.project.services;

import com.hypeflame.project.entities.Client;
import com.hypeflame.project.entities.Order;
import com.hypeflame.project.entities.Payment;
import com.hypeflame.project.entities.enums.OrderStatus;
import com.hypeflame.project.entities.enums.PaymentStatus;
import com.hypeflame.project.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ModelMapper modelMapper;

    //TODO
    public void payOrder(Long orderId){
        Order order = findById(orderId);
        order.setOrderStatus(OrderStatus.FINISHED);
        Payment payment = order.getPayment();
        payment.setPaymentStatus(PaymentStatus.PAGO);
        payment.setPaymentDate(new Date());
        orderRepository.save(order);
    }

    public void newOrder(Order obj, Client client){
        Order order = obj;
        order.setOrderStatus(OrderStatus.BUDGET_ANALYSING);
        order.setOrderDate(new Date());
        order.setClient(client);
        Order savedOrder = save(order);
        Payment payment = new Payment(savedOrder.getId(), new Date(0), PaymentStatus.ABERTO, savedOrder);
        order.setPayment(payment);
        orderRepository.save(order);
    }

    public List<Order> findAll(){
        List<Order> orderList = orderRepository.findAll();
        return orderList;
    }

    public Order findById(Long id){
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow();
    }

    public Order save(Order order){
        orderRepository.save(order);
        return order;
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
