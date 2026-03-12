package com.github.romis0203.ms.restaurante.controller;

import com.github.romis0203.ms.restaurante.dto.RestauranteDTO;
import com.github.romis0203.ms.restaurante.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService service;

    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> getAllRestaurantes() {
        return ResponseEntity.ok(service.findAllRestaurantes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDTO> getRestauranteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findRestauranteById(id));
    }

    @PostMapping
    public ResponseEntity<RestauranteDTO> createRestaurante(@RequestBody @Valid RestauranteDTO dto) {
        dto = service.saveRestaurante(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteDTO> updateRestaurante(@PathVariable Long id, @RequestBody @Valid RestauranteDTO dto) {
        return ResponseEntity.ok(service.updateRestaurante(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestauranteById(@PathVariable Long id) {
        service.deleteRestauranteById(id);
        return ResponseEntity.noContent().build();
    }
}