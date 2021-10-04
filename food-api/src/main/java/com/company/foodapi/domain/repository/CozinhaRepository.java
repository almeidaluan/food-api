package com.company.foodapi.domain.repository;

import com.company.foodapi.domain.model.Cozinha;

import javax.transaction.Transactional;
import java.util.List;

public interface CozinhaRepository {

    List<Cozinha> listarCozinhas();

    void cadastroCozinha(Cozinha cozinha);

    void atualizaCozinha(Cozinha cozinha);

    Cozinha buscaCozinha(Long id);

    List<Cozinha> consultaPorNome();

    void  deletarCozinha(Cozinha cozinha);
}
