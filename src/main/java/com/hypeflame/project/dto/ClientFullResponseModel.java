package com.hypeflame.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientFullResponseModel {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    private Boolean adm;

}
