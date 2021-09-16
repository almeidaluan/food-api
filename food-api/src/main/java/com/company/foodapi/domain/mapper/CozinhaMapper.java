package com.company.foodapi.domain.mapper;

import com.company.foodapi.domain.dto.CozinhaDTO;
import com.company.foodapi.domain.dto.CreateRestauranteDTO;
import com.company.foodapi.domain.model.Cozinha;
import com.company.foodapi.domain.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CozinhaMapper {


    CozinhaDTO CozinhaToCozinhaDTO(Cozinha cozinha);
    Cozinha CozinhaDTOToCozinha(CozinhaDTO cozinhaDTO);
}