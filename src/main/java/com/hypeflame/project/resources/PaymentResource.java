package com.hypeflame.project.resources;

import com.hypeflame.project.entities.Payment;
import com.hypeflame.project.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

    @Autowired
    private PaymentService paymentService;

    public ResponseEntity<List<Payment>> findAll(){
        List<Payment> paymentList = paymentService.findAll();
        return ResponseEntity.ok().body(paymentList);
    }
}
