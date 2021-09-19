package com.company.foodapi.domain.dto;

import com.company.foodapi.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CozinhaDTO {

    public Long id;
    public String nome;

    public CozinhaDTO(Long id){
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
