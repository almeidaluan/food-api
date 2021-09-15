package com.company.foodapi.infrastructure.repository;

import com.company.foodapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CozinhaRepository02 extends JpaRepository<Cozinha,Long> {

    @Query("SELECT obj FROM Cozinha obj JOIN FETCH obj.restaurantes")
    Cozinha findRestaurantesCozinha();
}
