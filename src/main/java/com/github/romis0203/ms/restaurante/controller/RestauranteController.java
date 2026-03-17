package com.github.romis0203.ms.restaurante.controller;

import com.github.romis0203.ms.restaurante.dto.RestauranteDTO;
import com.github.romis0203.ms.restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")

public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> getAllRestaurantes(){

        List<RestauranteDTO> restaurantes = restauranteService.findAllRestaurantes();

        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDTO> getRestauranteById(@PathVariable Long id){

        RestauranteDTO restauranteDTO = restauranteService.findRestauranteById(id);
        return ResponseEntity.ok(restauranteDTO);
    }


}
