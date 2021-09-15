package com.company.foodapi.di.notificacao;

import com.company.foodapi.di.modelo.Cliente;

public class NotificadorSMS implements Notificador{
    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando %s através do SMS %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}
