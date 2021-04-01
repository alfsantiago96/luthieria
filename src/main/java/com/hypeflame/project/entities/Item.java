package com.hypeflame.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String service;
    private String type;
    private String model;
    private String serviceDescription;
    private Double servicePrice;

    @JsonIgnore
    @ManyToOne
    private Order order;
}
