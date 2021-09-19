package com.company.foodapi.controller;

import com.company.foodapi.di.config.AlgaConfig;
import com.company.foodapi.di.service.AtivacaoClienteService;

import com.company.foodapi.domain.model.Cozinha;
import com.company.foodapi.domain.repository.CozinhaRepository;
import com.company.foodapi.infrastructure.repository.CozinhaRepository02;
import com.company.foodapi.infrastructure.repository.RestauranteRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class TesteControllerDI {

    private final AtivacaoClienteService ativacaoClienteService;
    private final AlgaConfig config;
    private final RestauranteRepository restauranteRepository;

    private final CozinhaRepository02 cozinhaRepository;


    public TesteControllerDI(AtivacaoClienteService ativacaoClienteService, AlgaConfig config, RestauranteRepository restauranteRepository, CozinhaRepository02 cozinhaRepository){
        this.ativacaoClienteService = ativacaoClienteService;
        this.config = config;
        this.restauranteRepository = restauranteRepository;
        this.cozinhaRepository = cozinhaRepository;
    }
    @GetMapping
     public String Hello(){
        Cozinha cozinha = new Cozinha();
        //cozinha.getCozinhaEmbedded().setNome("Tailandesa");

        cozinhaRepository.save(cozinha);
        return "nada";
    }

}
