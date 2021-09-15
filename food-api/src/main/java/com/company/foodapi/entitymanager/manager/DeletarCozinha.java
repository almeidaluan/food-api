package com.company.foodapi.entitymanager.manager;

import com.company.foodapi.FoodApiApplication;
import com.company.foodapi.domain.model.Cozinha;
import com.company.foodapi.domain.repository.CozinhaRepository;
import com.company.foodapi.entitymanager.CozinhaEntityManager;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class DeletarCozinha {

    public static void main(String[] args){

        ApplicationContext context = new SpringApplicationBuilder(FoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhaEntityManager = context.getBean(CozinhaRepository.class);

        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);

        cozinhaEntityManager.deletarCozinha(cozinha);

        var cozinhas = cozinhaEntityManager.listarCozinhas();

        for (var cozinha01: cozinhas) {
            System.out.println(cozinha01.getNome());
        }

    }
}
