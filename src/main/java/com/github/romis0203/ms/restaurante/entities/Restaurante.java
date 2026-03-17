package com.github.romis0203.ms.restaurante.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "tb_restaurante")

public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;
    private String cidade;
    private String uf;

    //relacionamento entre um restaurante possuir várias reservas, quase sempre será um Array com
    // o objeto que possui o @joinColumn
    @OneToMany(mappedBy = "restaurante")
    private List<Reserva> reservas = new ArrayList<>();
}
