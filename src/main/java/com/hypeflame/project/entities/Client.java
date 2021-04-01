package com.hypeflame.project.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    private Boolean adm;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Order> orderList = new ArrayList<>();

    public Client(Long id, String name, String email, String phone, String cpf, Boolean adm) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cpf = cpf;
        this.adm = adm;
    }
}
