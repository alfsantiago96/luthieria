package com.hypeflame.project.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hypeflame.project.entities.enums.PaymentStatus;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_payment")
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date paymentDate;
    @Enumerated
    private PaymentStatus paymentStatus = PaymentStatus.ABERTO;

    @JsonIgnore
    @OneToOne
    @MapsId
    private Order order;



}
