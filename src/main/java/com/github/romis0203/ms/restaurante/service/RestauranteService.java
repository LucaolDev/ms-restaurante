package com.github.romis0203.ms.restaurante.service;

import com.github.romis0203.ms.restaurante.dto.RestauranteDTO;
import com.github.romis0203.ms.restaurante.entities.Restaurante;
import com.github.romis0203.ms.restaurante.exceptions.ResourceNotFoundException;
import com.github.romis0203.ms.restaurante.repositories.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RestauranteService {

    @Autowired //usado para criar automaticamente os objetos, sem precisar instanciar manualmente
    private RestauranteRepository restauranteRepository;

    @Transactional(readOnly = true)
    public List<RestauranteDTO> findAllRestaurantes(){
        return restauranteRepository.findAll().stream().map(RestauranteDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public RestauranteDTO findRestauranteById(Long id){

        Restaurante restaurante = restauranteRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id));

        return new RestauranteDTO(restaurante);
    }

}
