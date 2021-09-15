package com.company.foodapi.di.notificacao;

import com.company.foodapi.di.modelo.Cliente;

public interface Notificador {

    void notificar(Cliente cliente, String mensagem);
}
