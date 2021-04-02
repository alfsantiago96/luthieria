package com.hypeflame.project.dto;

import com.hypeflame.project.entities.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestModel {

    private final Long id = null;
    private Long orderId;
    private String service;
    private String type;
    private String model;
    private String serviceDescription;
    private Double servicePrice;
}
