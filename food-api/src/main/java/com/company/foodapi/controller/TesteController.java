package com.company.foodapi.controller;

import com.company.foodapi.domain.dto.CozinhaDTO;
import com.company.foodapi.domain.repository.CozinhaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/testes")
public class TesteController {

    public final CozinhaRepository cozinhaRepository;

    public TesteController(CozinhaRepository cozinhaRepository){

        this.cozinhaRepository = cozinhaRepository;
    }

    @GetMapping
    public ResponseEntity<?> teste(){
        var teste = cozinhaRepository.consultaPorNome();

        var teste02 = teste.stream().map( x -> new CozinhaDTO(x.getNome())).collect(Collectors.toList());
        return ResponseEntity.ok(teste02);
    }
}
