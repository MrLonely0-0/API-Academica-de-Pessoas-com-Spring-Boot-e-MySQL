package com.universidade.pessoas.service;

import com.universidade.pessoas.dto.PessoaDTO;
import com.universidade.pessoas.model.Pessoa;
import com.universidade.pessoas.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarPessoaComSucesso() {
        PessoaDTO dto = new PessoaDTO();
        dto.setNome("Maria");
        dto.setCpf("12345678901");
        dto.setIdade(20);

        Pessoa pessoa = new Pessoa();
        pessoa.setId(UUID.randomUUID());
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setIdade(dto.getIdade());

        Mockito.when(pessoaRepository.save(Mockito.any(Pessoa.class))).thenReturn(pessoa);
        Mockito.when(pessoaRepository.existsByCpf(dto.getCpf())).thenReturn(false);

        Pessoa resultado = pessoaService.salvar(dto);

        assertEquals(dto.getNome(), resultado.getNome());
    }
}