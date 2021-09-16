package com.company.foodapi.domain.dto;

import com.company.foodapi.domain.mapper.CozinhaMapper;
import com.company.foodapi.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@JsonRootName("restaurante")
@Data
public class RestauranteDTO{

    @JsonIgnore
    private Long id;

    @JsonProperty("titulo")
    public String name;

    public BigDecimal taxaFrete;

    public CozinhaDTO cozinha;

    public RestauranteDTO(Restaurante restaurante){
        CozinhaMapper cozinhaMapper = Mappers.getMapper(CozinhaMapper.class);
        this.id = restaurante.getId();
        this.name = restaurante.getName();
        this.taxaFrete = restaurante.getTaxaFrete();
        this.cozinha = cozinhaMapper.CozinhaToCozinhaDTO(restaurante.getCozinha());
    }


}
