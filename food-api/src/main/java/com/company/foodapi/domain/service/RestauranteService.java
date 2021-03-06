package com.company.foodapi.domain.service;


import com.company.foodapi.domain.dto.CreateRestauranteDTO;
import com.company.foodapi.domain.dto.RestauranteDTO;
import com.company.foodapi.domain.dto.UpdateRestaurante;
import com.company.foodapi.domain.exceptions.ResourceNotFound;
import com.company.foodapi.domain.mapper.CozinhaMapper;
import com.company.foodapi.domain.mapper.RestauranteMapper;
import com.company.foodapi.domain.model.Cozinha;
import com.company.foodapi.domain.model.Restaurante;
import com.company.foodapi.infrastructure.repository.CozinhaRepository02;
import com.company.foodapi.infrastructure.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    private final CozinhaRepository02 cozinhaRepository;

    public RestauranteService(RestauranteRepository restauranteRepository, CozinhaRepository02 cozinhaRepository) {
        this.restauranteRepository = restauranteRepository;
        this.cozinhaRepository = cozinhaRepository;
    }


    public Page<RestauranteDTO> restaurantes(Integer page, Integer size, String field, String direction){
        RestauranteMapper mapper = Mappers.getMapper(RestauranteMapper.class);

        HashMap<String, Sort.Direction> directions = new HashMap<String, Sort.Direction>();
        directions.put("asc", Sort.Direction.ASC);
        directions.put("desc", Sort.Direction.DESC);

        PageRequest pageRequest = PageRequest.of(page, size, directions.get(direction), field);

        return restauranteRepository.BuscaTodosRestaurantes(pageRequest).map(RestauranteDTO::new);
    }

    public RestauranteDTO findById(Long id){

        var restaurante = restauranteRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFound("N??o foi encontrado um restaurante com esse id"));

        return new RestauranteDTO(restaurante);

    }

    public Restaurante createRestaurante(CreateRestauranteDTO createRestauranteDTO){

        RestauranteMapper mapper = Mappers.getMapper(RestauranteMapper.class);
        CozinhaMapper cozinhaMapper = Mappers.getMapper(CozinhaMapper.class);

        Long id = createRestauranteDTO.getCozinha().getId();

        var cozinha = this.cozinhaRepository.findById(id).orElseThrow(() -> new ResourceNotFound("N??o foi encontrado uma cozinha com esse id"));

        createRestauranteDTO.setCozinha(cozinhaMapper.CozinhaToCozinhaDTO(cozinha));

        return this.restauranteRepository.save(mapper.CreateRestauranteDTOToRestaurante(createRestauranteDTO));
    }

    public RestauranteDTO updateRestaurante(Long id, UpdateRestaurante updateRestaurante){
        RestauranteMapper mapper = Mappers.getMapper(RestauranteMapper.class);

        var cozinhaId = updateRestaurante.getCozinha().getId();

        Restaurante restauranteAtual = restauranteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("N??o foi encontrado um restaurante com esse id"));

        Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
                .orElseThrow(() -> new ResourceNotFound("N??o foi encontrado uma cozinha com esse id"));

        if (updateRestaurante != null) {

            var teste = mapper.UpdateRestauranteToRestaurante(updateRestaurante);

            BeanUtils.copyProperties(teste, restauranteAtual, "id");

            restauranteAtual = restauranteRepository.save(restauranteAtual);

            return new RestauranteDTO(restauranteAtual);
        }
        return null;
    }

    public void deleteRestaurante(Long id){
        var restaurante = this.restauranteRepository
                .findById(id).
                orElseThrow(() -> new ResourceNotFound("N??o foi encontrado um restaurante com esse id"));

        this.restauranteRepository.delete(restaurante);
    }


    public void atualizarParcial(Long restauranteId, Map<String, Object> campos) {
        RestauranteMapper mapper = Mappers.getMapper(RestauranteMapper.class);

        RestauranteDTO restauranteAtual = findById(restauranteId);

        //campos.forEach();

        var updateRestaurante = mapper.RestauranteDTOToUpdateRestaurante(restauranteAtual);

        merge(campos, updateRestaurante);

        updateRestaurante(restauranteId, updateRestaurante);
    }


    private void merge(Map<String, Object> dadosOrigem, UpdateRestaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        UpdateRestaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, UpdateRestaurante.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(UpdateRestaurante.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

//			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }
}
