package com.company.foodapi.infrastructure.repository;

import com.company.foodapi.domain.model.Restaurante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante,Long> {


    @Query(
            value = "select restaurante from Restaurante restaurante left join fetch restaurante.cozinha"
    ,countQuery = "select count(restaurante) from Restaurante restaurante left join restaurante.cozinha")
    Page<Restaurante> BuscaTodosRestaurantes(Pageable pageable);
}
