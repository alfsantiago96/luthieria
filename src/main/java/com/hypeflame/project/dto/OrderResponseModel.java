package com.hypeflame.project.dto;

import com.hypeflame.project.entities.Item;
import com.hypeflame.project.entities.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderResponseModel {

    private String id;
    private Date moment;
    private OrderStatus orderStatus;
    private String clientId;
    private String clientName;
    private String clientCpf;
    private Double total;

}
