package com.hypeflame.project.dto;

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

}
