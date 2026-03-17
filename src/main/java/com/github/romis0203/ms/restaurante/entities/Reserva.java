package com.github.romis0203.ms.restaurante.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

//anotations para gerar tudo bonitinho automatico
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_reserva")

public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataReserva;
    private String nomeCliente;
    private Integer qtdePessoas;


    //relacionamento de muitas reservas para um restaurante.
    //serve para atribuir uma reserva a um restaurante pelo ID, puxando os dados posteriormente
    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante;


}
