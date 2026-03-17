package com.github.romis0203.ms.restaurante.dto;

import com.github.romis0203.ms.restaurante.entities.Reserva;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class ReservaDTO {

    private Long id;

    @NotBlank(message = "campo é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve conter entre 3 a 100 caracteres")
    private String nomeCliente;

    @NotBlank(message = "campo é obrigatório")
    @Size(min = 1, message = "A reserva deve ter no mínimo uma pessoa")
    private Integer qtdepessoas;

    //@Future //usado para não aceitar data anterior a de hoje.
    @NotBlank(message = "campo é obrigatório")
    private LocalDate dataReserva;

    @NotNull(message = "campo restaurante é obrigatório")
    private RestauranteDTO restaurante;

    public ReservaDTO(Reserva reserva){
        id = reserva.getId();
        nomeCliente = reserva.getNomeCliente();
        qtdepessoas = reserva.getQtdePessoas();
        dataReserva = reserva.getDataReserva();
        restaurante = new RestauranteDTO(reserva.getRestaurante());
    }
}
