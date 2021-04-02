package com.hypeflame.project.dto;

import com.hypeflame.project.entities.Payment;
import com.hypeflame.project.entities.enums.OrderStatus;
import com.hypeflame.project.entities.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class OrderResponseModel {

    private String orderId;
    private String clientId;
    private String clientName;
    private Date moment;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private Integer totalItems;
    private Double total;

}
