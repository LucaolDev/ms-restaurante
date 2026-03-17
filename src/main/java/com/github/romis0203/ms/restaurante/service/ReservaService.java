package com.github.romis0203.ms.restaurante.service;

import com.github.romis0203.ms.restaurante.dto.ReservaDTO;
import com.github.romis0203.ms.restaurante.entities.Reserva;
import com.github.romis0203.ms.restaurante.exceptions.ResourceNotFoundException;
import com.github.romis0203.ms.restaurante.repositories.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Transactional(readOnly = true)
    public List<ReservaDTO> findAllReservas(){

        return reservaRepository.findAll().stream().map(ReservaDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ReservaDTO findReservaById(Long id){

        Reserva reserva = reservaRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("recurso não encontrado. ID: " + id));

        return new ReservaDTO(reserva);
    }

    @Transactional
    public ReservaDTO saveReserva(ReservaDTO inputDTO){
        Reserva reserva = new Reserva();
        copyDtoToReserva(inputDTO, reserva);
        reserva = reservaRepository.save(reserva);
        return new ReservaDTO(reserva);

    }

    private void copyDtoToReserva(ReservaDTO inputDTO, Reserva reserva){reserva.setNomeCliente(inputDTO.getNomeCliente());}

    @Transactional
    public ReservaDTO updateReserva(Long id, ReservaDTO inputDto){

        try{
            Reserva reserva = reservaRepository.getReferenceById(id);
            copyDtoToReserva(inputDto, reserva);
            reserva = reservaRepository.save(reserva);
            return new ReservaDTO(reserva);
        } catch (EntityNotFoundException ex){
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

    @Transactional
    public void deleteReservaById(Long id){
        if(!reservaRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
        reservaRepository.deleteById(id);
    }
}
