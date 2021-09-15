package com.company.foodapi.controller;

import com.company.foodapi.di.config.AlgaConfig;
import com.company.foodapi.di.modelo.Cliente;
import com.company.foodapi.di.notificacao.NotificadorSMS;
import com.company.foodapi.di.service.AtivacaoClienteService;
import com.company.foodapi.domain.dto.RestauranteDTO;

import com.company.foodapi.infrastructure.repository.RestauranteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/")
public class TesteControllerDI {

    private final AtivacaoClienteService ativacaoClienteService;
    private final AlgaConfig config;
    private final RestauranteRepository restauranteRepository;


    public TesteControllerDI(AtivacaoClienteService ativacaoClienteService, AlgaConfig config, RestauranteRepository restauranteRepository){
        this.ativacaoClienteService = ativacaoClienteService;
        this.config = config;
        this.restauranteRepository = restauranteRepository;

    }
    @GetMapping
    public String Hello(){
        AtivacaoClienteService ativacao = new AtivacaoClienteService(config.notificadorEmail());
        AtivacaoClienteService ativacao02 = new AtivacaoClienteService(new NotificadorSMS());

        Cliente cliente = new Cliente("Luan","almeida.luan099@gmail.com","21985821462");
        ativacao.ativar(cliente);
        ativacao02.ativar(cliente);
        return "Hello World !!!";

    }







}
