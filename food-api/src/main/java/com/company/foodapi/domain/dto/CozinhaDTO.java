package com.company.foodapi.domain.dto;

import com.company.foodapi.domain.model.Cozinha;

public class CozinhaDTO {

    private String nome;

    public CozinhaDTO(){

    }

    public CozinhaDTO(Cozinha cozinha){
        this.nome = cozinha.getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
