package com.company.foodapi.domain.dto;

import com.company.foodapi.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.math.BigDecimal;

@JsonRootName("restaurante")
public class RestauranteDTO{

    @JsonIgnore
    private Long id;

    @JsonProperty("titulo")
    public String name;

    public BigDecimal taxaFrete;

    public CozinhaDTO cozinha;

    public RestauranteDTO(Restaurante restaurante){
        this.taxaFrete = restaurante.getTaxaFrete();
        this.name = restaurante.getName();
        this.cozinha = new CozinhaDTO(restaurante.getCozinha());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CozinhaDTO getCozinha() {
        return cozinha;
    }

    public void setCozinha(CozinhaDTO cozinha) {
        this.cozinha = cozinha;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }
}
