package com.hypeflame.project.dto;

import com.hypeflame.project.entities.Client;
import com.hypeflame.project.entities.Item;
import com.hypeflame.project.entities.Payment;
import com.hypeflame.project.entities.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderFullResponseModel {

    private String id;
    private OrderStatus orderStatus;
    private Date orderDate;
    private Client client;
    private Payment payment;
    private Double total;

    private List<Item> itemList;

}
