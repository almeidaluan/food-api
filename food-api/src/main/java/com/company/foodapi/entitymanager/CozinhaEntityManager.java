package com.company.foodapi.entitymanager;

import com.company.foodapi.domain.model.Cozinha;
import com.company.foodapi.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class CozinhaEntityManager implements CozinhaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cozinha> listarCozinhas(){
        return entityManager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    @Transactional
    public void cadastroCozinha(Cozinha cozinha){
        entityManager.merge(cozinha);
    }

    @Transactional
    public void atualizaCozinha(Cozinha cozinha){
        entityManager.merge(cozinha);
    }

    public Cozinha buscaCozinha(Long id){
        return entityManager.find(Cozinha.class,id);
    }


    @Transactional
    public void  deletarCozinha(Cozinha cozinha){
        var cozinhaDeleted = buscaCozinha(cozinha.getId());
        entityManager.remove(cozinhaDeleted);
    }

}
