package com.company.foodapi.entitymanager.manager;


import com.company.foodapi.FoodApiApplication;
import com.company.foodapi.domain.model.Cozinha;
import com.company.foodapi.domain.repository.CozinhaRepository;
import com.company.foodapi.entitymanager.CozinhaEntityManager;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class CadastroCozinha {

    public static void main(String[] args){
        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhaEntityManager = applicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Brasileira");

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Portuguesa");

        cozinhaEntityManager.cadastroCozinha(cozinha2);
        cozinhaEntityManager.cadastroCozinha(cozinha);

        for (var cozinhaItem : cozinhaEntityManager.listarCozinhas()) {
            System.out.println(cozinhaItem.getNome());
        }


    }
}
