package com.hypeflame.project.dto;

import com.hypeflame.project.entities.enums.OrderStatus;
import com.hypeflame.project.entities.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class OrderPaydResponseModel {

    private final String l = " # OBRIGADO PELA PREFERENCIA # ";
    private String clientName;
    private String clientCPF;
    private final String i = " # DADOS DO PAGAMENTO # ";
    private String paymentId;
    private Date paymentDate;
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
    private Double total;
}
