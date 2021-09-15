package com.company.foodapi.domain.dto;

import com.company.foodapi.domain.model.Cozinha;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateRestauranteDTO {

    private BigDecimal taxaFrete;
    private String name;
    private Cozinha cozinha;

}
