package com.github.romis0203.ms.restaurante.dto;

import com.github.romis0203.ms.restaurante.entities.Reserva;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    @NotNull(message = "Campo data da reserva é requerido")
    @FutureOrPresent(message = "A data deve ser maior ou igual à data atual")
    private LocalDate dataReserva;

    @NotBlank(message = "Campo nome do cliente é requerido")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nomeCliente;

    @NotNull(message = "Campo quantidade de pessoas é requerido")
    @Positive(message = "A quantidade de pessoas deve ser maior que zero")
    private Integer qtdePessoas;

    @NotNull(message = "Campo restaurante é requerido")
    private RestauranteDTO restaurante;

    public ReservaDTO(Reserva reserva) {
        id = reserva.getId();
        dataReserva = reserva.getDataReserva();
        nomeCliente = reserva.getNomeCliente();
        qtdePessoas = reserva.getQtdePessoas();
        restaurante = new RestauranteDTO(reserva.getRestaurante());
    }
}