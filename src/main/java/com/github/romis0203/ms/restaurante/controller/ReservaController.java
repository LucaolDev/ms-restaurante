package com.github.romis0203.ms.restaurante.controller;

import com.github.romis0203.ms.restaurante.dto.ReservaDTO;
import com.github.romis0203.ms.restaurante.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> getAllReservas() {
        return ResponseEntity.ok(service.findAllReservas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReservaById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findReservaById(id));
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> createReserva(@RequestBody @Valid ReservaDTO dto) {
        dto = service.saveReserva(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> updateReserva(@PathVariable Long id, @RequestBody @Valid ReservaDTO dto) {
        return ResponseEntity.ok(service.updateReserva(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservaById(@PathVariable Long id) {
        service.deleteReservaById(id);
        return ResponseEntity.noContent().build();
    }
}