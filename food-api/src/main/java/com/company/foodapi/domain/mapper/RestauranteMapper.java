package com.company.foodapi.domain.mapper;

import com.company.foodapi.domain.dto.CreateRestauranteDTO;
import com.company.foodapi.domain.dto.RestauranteDTO;
import com.company.foodapi.domain.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

    @Mappings({
            @Mapping(target="taxaFrete", source="createRestauranteDTO.taxaFrete"),
            @Mapping(target="name", source="createRestauranteDTO.name"),
            @Mapping(target="cozinha", source="createRestauranteDTO.cozinha")
    })
    Restaurante CreateRestauranteDTOToRestaurante(CreateRestauranteDTO createRestauranteDTO);

    Restaurante RestauranteDTOtoRestaurante(RestauranteDTO restauranteDTO);

    RestauranteDTO RestaurantetoRestauranteDTO(Restaurante restaurante);
}
