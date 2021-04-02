package com.hypeflame.project.dto;

import com.hypeflame.project.entities.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRemoveRequestModel {

    private Long itemId;
}
