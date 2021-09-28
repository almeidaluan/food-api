package com.company.foodapi.controller;

import com.company.foodapi.domain.dto.CreateRestauranteDTO;
import com.company.foodapi.domain.dto.RestauranteDTO;
import com.company.foodapi.domain.dto.UpdateRestaurante;
import com.company.foodapi.domain.exceptions.ResourceNotFound;
import com.company.foodapi.domain.model.Restaurante;
import com.company.foodapi.domain.service.RestauranteService;
import com.company.foodapi.infrastructure.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService){
        this.restauranteService = restauranteService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Page<RestauranteDTO>> restaurantes(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                             @RequestParam(value = "field") String field,
                                             @RequestParam(value = "direction") String direction){

        return ResponseEntity.ok().body(this.restauranteService.restaurantes(page,size,field,direction));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RestauranteDTO> findById(@PathVariable Long id){

        HttpHeaders header = new HttpHeaders();
        header.add("MinhaChave","MeuValor");
        return ResponseEntity.status(HttpStatus.OK).headers(header).body(restauranteService.findById(id));
    }


    @PostMapping
    public ResponseEntity<Void> createRestaurante(@RequestBody CreateRestauranteDTO createRestauranteDTO){

         this.restauranteService.createRestaurante(createRestauranteDTO);
         return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteDTO> updateRestaurante(@PathVariable Long id, @RequestBody UpdateRestaurante updateRestaurante){

        return ResponseEntity.status(HttpStatus.OK).body(this.restauranteService.updateRestaurante(id,updateRestaurante));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurante(@PathVariable Long id){

        this.restauranteService.deleteRestaurante(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String,Object> campos){
        this.restauranteService.atualizarParcial(restauranteId,campos);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
