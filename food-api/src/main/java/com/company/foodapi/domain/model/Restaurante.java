package com.company.foodapi.domain.model;

import com.company.foodapi.domain.dto.CozinhaDTO;
import com.company.foodapi.domain.dto.CreateRestauranteDTO;
import lombok.*;
import org.hibernate.annotations.Proxy;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tab_restaurante")
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;


    @Column(name = "nome")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id")
    private Cozinha cozinha;

}
