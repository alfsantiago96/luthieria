package com.hypeflame.project.services;

import com.hypeflame.project.entities.Payment;
import com.hypeflame.project.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderService orderService;

    public List<Payment> findAll(){
        List<Payment> paymentList = paymentRepository.findAll();
        return paymentList;
    }

    public Payment findById(Long id){
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.orElseThrow();
    }

    public void delete(Long id){
        paymentRepository.deleteById(id);
    }
}
