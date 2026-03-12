package com.github.romis0203.ms.restaurante.service;

import com.github.romis0203.ms.restaurante.dto.RestauranteDTO;
import com.github.romis0203.ms.restaurante.entities.Restaurante;
import com.github.romis0203.ms.restaurante.exceptions.DatabaseException;
import com.github.romis0203.ms.restaurante.exceptions.ResourceNotFoundException;
import com.github.romis0203.ms.restaurante.repositories.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository repository;

    @Transactional(readOnly = true)
    public List<RestauranteDTO> findAllRestaurantes() {
        return repository.findAll().stream().map(RestauranteDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public RestauranteDTO findRestauranteById(Long id) {
        Restaurante restaurante = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id)
        );
        return new RestauranteDTO(restaurante);
    }

    @Transactional
    public RestauranteDTO saveRestaurante(RestauranteDTO dto) {
        Restaurante restaurante = new Restaurante();
        copyDtoToRestaurante(dto, restaurante);
        restaurante = repository.save(restaurante);
        return new RestauranteDTO(restaurante);
    }

    @Transactional
    public RestauranteDTO updateRestaurante(Long id, RestauranteDTO dto) {
        try {
            Restaurante restaurante = repository.getReferenceById(id);
            copyDtoToRestaurante(dto, restaurante);
            restaurante = repository.save(restaurante);
            return new RestauranteDTO(restaurante);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteRestauranteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não foi possível excluir o restaurante. Existem reservas associadas a ele.");
        }
    }

    private void copyDtoToRestaurante(RestauranteDTO dto, Restaurante restaurante) {
        restaurante.setNome(dto.getNome());
        restaurante.setEndereco(dto.getEndereco());
        restaurante.setCidade(dto.getCidade());
        restaurante.setUf(dto.getUf());
    }
}