package com.faculdade.pessoa.service;

import com.faculdade.pessoa.model.Pessoa;
import com.faculdade.pessoa.repository.PessoaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class PessoaServiceTest {

    @Autowired
    PessoaService pessoaService;

    @Autowired
    PessoaRepository pessoaRepository;

    @Test
    void testBuscarPorCpf() {
        Pessoa pessoa = new Pessoa();
        pessoa.setName("Maria Teste");
        pessoa.setCpf("12345678900");
        pessoa.setAge(25);
        pessoaService.save(pessoa);

        Optional<Pessoa> encontrada = pessoaService.findByCpf("12345678900");
        Assertions.assertTrue(encontrada.isPresent());
    }
}
