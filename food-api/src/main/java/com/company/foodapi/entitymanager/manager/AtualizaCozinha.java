package com.company.foodapi.entitymanager.manager;

import com.company.foodapi.FoodApiApplication;
import com.company.foodapi.domain.repository.CozinhaRepository;
import com.company.foodapi.entitymanager.CozinhaEntityManager;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class AtualizaCozinha {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhaEntityManager = applicationContext.getBean(CozinhaRepository.class);

        var cozinha = cozinhaEntityManager.buscaCozinha(1L);

        System.out.println(cozinha.getNome());

        cozinha.setNome("Tailandesa alterado");

        cozinhaEntityManager.atualizaCozinha(cozinha);

        System.out.println(cozinhaEntityManager.buscaCozinha(1L).getNome());




    }
}
