package com.hypeflame.project.dto;

import com.hypeflame.project.entities.Item;
import com.hypeflame.project.entities.enums.OrderStatus;
import com.hypeflame.project.entities.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderItemListResponseModel {

    private String id;
    private OrderStatus orderStatus;
    private Date moment;
    private String clientId;
    private String clientName;
    private String clientCpf;
    private Integer totalItems;
    private Double total;
    private Long orderPaymentId;
    private PaymentStatus paymentStatus;
    private Date orderPaymentMoment;


    private List<Item> itemList;

}
