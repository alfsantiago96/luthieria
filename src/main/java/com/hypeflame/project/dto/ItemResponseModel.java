package com.hypeflame.project.dto;

import com.hypeflame.project.entities.Payment;
import com.hypeflame.project.entities.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemResponseModel {

    private String id;
    private String service;
    private String type;
    private String model;
    private String serviceDescription;
    private Double servicePrice;
    private String orderId;
    private String orderMoment;
    private PaymentStatus orderPaymentStatus;

}
