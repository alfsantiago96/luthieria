package com.hypeflame.project.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemUptadeRequestModel {

    @NotBlank
    private String serviceDescription;

    @NumberFormat
    @NotNull
    private Double servicePrice;
}
