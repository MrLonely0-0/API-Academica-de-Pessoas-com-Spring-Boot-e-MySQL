package com.universidade.pessoa.service;

import com.universidade.pessoa.dto.PessoaDTO;
import com.universidade.pessoa.model.Pessoa;
import com.universidade.pessoa.repository.PessoaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class PessoaServiceTest {

    @Autowired
    private PessoaService pessoaService;

    @MockBean
    private PessoaRepository pessoaRepository;

    @Test
    public void deveSalvarPessoaComSucesso() {
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

        Assertions.assertEquals(dto.getNome(), resultado.getNome());
    }
}
