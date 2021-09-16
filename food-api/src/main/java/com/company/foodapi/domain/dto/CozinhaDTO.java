package com.company.foodapi.domain.dto;

import com.company.foodapi.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CozinhaDTO {


    private Long id;
    private String nome;

    public CozinhaDTO(){

    }


}
