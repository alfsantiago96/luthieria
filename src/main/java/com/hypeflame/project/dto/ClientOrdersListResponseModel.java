package com.hypeflame.project.dto;

import com.hypeflame.project.entities.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ClientOrdersListResponseModel {

    private List<Order> orderList;
}
