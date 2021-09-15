package com.company.foodapi.di.notificacao;

import com.company.foodapi.di.config.propriedades.EmailProperties;
import com.company.foodapi.di.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


public class NotificadorEmail implements Notificador{

    private boolean caixaAlta;

    @Autowired
    private EmailProperties emailProperties;



    public void notificar(Cliente cliente, String mensagem) {
        System.out.println(emailProperties.getPortaServidor());
        System.out.println(emailProperties.getHostServidor());
        if(this.caixaAlta){
            mensagem = mensagem.toUpperCase();
        }
        System.out.printf("Notificando %s através do e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);

    }

    public boolean isCaixaAlta() {
        return caixaAlta;
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }

}
