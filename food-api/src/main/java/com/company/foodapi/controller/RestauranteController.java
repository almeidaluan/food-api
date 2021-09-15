package com.company.foodapi.controller;

import com.company.foodapi.domain.dto.CreateRestauranteDTO;
import com.company.foodapi.domain.dto.RestauranteDTO;
import com.company.foodapi.domain.dto.UpdateRestaurante;
import com.company.foodapi.domain.exceptions.ResourceNotFound;
import com.company.foodapi.domain.model.Restaurante;
import com.company.foodapi.infrastructure.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteRepository restauranteRepository;

    public RestauranteController(RestauranteRepository restauranteRepository){
        this.restauranteRepository = restauranteRepository;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<RestauranteDTO>> restaurantes(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                             @RequestParam(value = "field") String field,
                                             @RequestParam(value = "direction") String direction){

        HashMap<String, Sort.Direction> directions = new HashMap<String, Sort.Direction>();
        directions.put("asc", Sort.Direction.ASC);
        directions.put("desc", Sort.Direction.DESC);

        PageRequest pageRequest = PageRequest.of(page, size, directions.get(direction), field);

        return ResponseEntity.ok().body(restauranteRepository.BuscaTodosRestaurantes(pageRequest).map(RestauranteDTO::new));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RestauranteDTO> findById(@PathVariable Long id){
        var restaurante = restauranteRepository.findById(id);

        HttpHeaders header = new HttpHeaders();
        header.add("MinhaChave","MeuValor");
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(new RestauranteDTO(restaurante.get()));
    }


    @PostMapping
    public ResponseEntity<Void> createRestaurante(@RequestBody List<CreateRestauranteDTO> createRestauranteDTO){

    this.restauranteRepository.saveAll(createRestauranteDTO.stream()
            .map(Restaurante::new).collect(Collectors.toList()));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteDTO> updateRestaurante(@PathVariable Long id, @RequestBody UpdateRestaurante updateRestaurante){
        Restaurante restauranteAtual = restauranteRepository.getById(id);

        if (updateRestaurante != null) {

            BeanUtils.copyProperties(updateRestaurante, restauranteAtual, "id");
            
            restauranteAtual = restauranteRepository.save(restauranteAtual);

            return ResponseEntity.ok(new RestauranteDTO(restauranteAtual));
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurante(@PathVariable Long id){

        var restaurante = this.restauranteRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Não foi encontrado um restaurante com esse id"));

        this.restauranteRepository.delete(restaurante);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
