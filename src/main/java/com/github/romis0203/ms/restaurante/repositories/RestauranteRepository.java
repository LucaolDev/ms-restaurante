package com.github.romis0203.ms.restaurante.repositories;

import com.github.romis0203.ms.restaurante.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}
