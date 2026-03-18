package com.github.romis0203.ms.restaurante.dto;

import com.github.romis0203.ms.restaurante.entities.Restaurante;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class RestauranteDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O endereço é obrigatório")
    private String endereco;

    @NotBlank(message = "A cidade é obrigatória")
    @Size(min = 5, max = 100, message = "A cidade deve ter entre 5 e 100 caracteres")
    private String cidade;

    @NotBlank(message = "O UF é obrigatório")
    @Size(min = 2, max = 2, message = "O UF deve ter exatamente 2 caracteres")
    private String uf;

    public RestauranteDTO(Restaurante restaurante){
        id = restaurante.getId();
        nome = restaurante.getNome();
        endereco = restaurante.getEndereco();
        cidade = restaurante.getCidade();
        uf = restaurante.getUf();
    }
}
