package com.universidade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PessoaDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @NotBlank
    private String email;

    @NotNull(message = "O ID do curso é obrigatório")
    private Long cursoId;
}
