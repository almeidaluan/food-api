package com.company.foodapi.domain.dto;

import com.company.foodapi.domain.model.Cozinha;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRestaurante {

    public BigDecimal taxaFrete;

    public String name;

    public UpdateCozinhaDTO cozinha;

}
