package com.company.foodapi.di.config;

import com.company.foodapi.di.config.propriedades.EmailProperties;
import com.company.foodapi.di.notificacao.NotificadorEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlgaConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public NotificadorEmail notificadorEmail(){

        NotificadorEmail notificadorEmail = new NotificadorEmail();
        notificadorEmail.setCaixaAlta(true);
        return notificadorEmail;
    }

}
