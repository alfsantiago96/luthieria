package com.hypeflame.project.dto;

import com.hypeflame.project.entities.Client;
import com.hypeflame.project.entities.Item;
import com.hypeflame.project.entities.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderRequestModel {

    private String Id;
    private Date moment;
    private OrderStatus orderStatus;
    private Client client;

}
