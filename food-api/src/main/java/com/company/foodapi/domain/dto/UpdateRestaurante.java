package com.company.foodapi.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateRestaurante {

    public BigDecimal taxaFrete;

    public String name;


}
