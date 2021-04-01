package com.hypeflame.project.dto;

import com.hypeflame.project.entities.Client;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequestModel {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
}
