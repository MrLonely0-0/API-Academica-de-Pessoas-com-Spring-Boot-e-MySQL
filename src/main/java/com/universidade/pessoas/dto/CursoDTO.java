package com.universidade.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CursoDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;
}
