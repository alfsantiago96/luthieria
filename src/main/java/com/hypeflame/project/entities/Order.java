package com.hypeflame.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hypeflame.project.entities.enums.OrderStatus;
import com.hypeflame.project.entities.enums.PaymentStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date moment;
    @Enumerated
    private OrderStatus orderStatus;

    @JsonIgnore
    @ManyToOne
    private Client client;

    @JsonIgnore
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<Item> itemList = new ArrayList<>();

    public Order(Long id, Date moment, OrderStatus orderStatus, Client client) {
        this.id = id;
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.client = client;
    }

    private Double total = getTotal();

    public Double getTotal(){
        double total = 0;
        for (Item items : itemList){
            total += items.getServicePrice();
        }
        return total;
    }
}
