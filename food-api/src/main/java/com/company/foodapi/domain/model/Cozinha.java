package com.company.foodapi.domain.model;

import com.company.foodapi.domain.dto.CozinhaDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tab_cozinha")
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nm_cozinha",unique = true,updatable = true)
    private String nome;


    @OneToMany(mappedBy = "cozinha")
    private List<Restaurante> restaurantes = new ArrayList<>();


    public Cozinha(CozinhaDTO cozinhaDTO){
        this.id = cozinhaDTO.getId();
    }
}
