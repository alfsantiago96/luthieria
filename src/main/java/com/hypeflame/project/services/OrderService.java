package com.hypeflame.project.services;

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
        payment.setMoment(new Date());
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

    //TODO
    public Order insert(Order obj){
        Order order = obj;
        Payment payment = order.getPayment();
        payment.setId(order.getId());;
        payment.setPaymentStatus(PaymentStatus.ABERTO);
        payment.setMoment(new Date());
        payment.setOrder(order);
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
