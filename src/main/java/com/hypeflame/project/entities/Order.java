package com.hypeflame.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hypeflame.project.entities.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date orderDate;
    @Enumerated
    private OrderStatus orderStatus;

    @JsonIgnore
    @ManyToOne
    private Client client;


    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<Item> itemList = new ArrayList<>();

    public Order(Long id, Date orderDate, OrderStatus orderStatus, Client client) {
        this.id = id;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.client = client;
    }

    private Double total = getTotal();
    private Integer totalItems = getTotalItems();

    public Integer getTotalItems(){
        Integer n = itemList.size();
        return n;
    }

    public Double getTotal(){
        double total = 0;
        for (Item items : itemList){
            total += items.getServicePrice();
        }
        return total;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId().equals(order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
