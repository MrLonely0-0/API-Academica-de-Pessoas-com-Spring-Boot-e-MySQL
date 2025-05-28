package com.faculdade.pessoa.service;

import com.faculdade.pessoa.dto.PessoaDTO;
import com.faculdade.pessoa.model.Pessoa;
import com.faculdade.pessoa.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private static final Logger logger = LoggerFactory.getLogger(PessoaService.class);
    private final PessoaRepository pessoaRepository;

    @Transactional
    public Pessoa save(Pessoa pessoa) {
        logger.info("Salvando pessoa com CPF: {}", pessoa.getCpf());

        if (pessoa.getAge() < 18) {
            logger.warn("Tentativa de cadastro com idade menor que 18");
            throw new RuntimeException("Pessoa deve ter idade mínima de 18 anos");
        }

        Optional<Pessoa> existente = pessoaRepository.findByCpf(pessoa.getCpf());
        if (existente.isPresent()) {
            throw new RuntimeException("CPF já cadastrado.");
        }

        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> listAll() {
        logger.info("Listando todas as pessoas");
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> findById(UUID id) {
        logger.info("Buscando pessoa por ID: {}", id);
        return pessoaRepository.findById(id);
    }

    public Optional<Pessoa> findByCpf(String cpf) {
        logger.info("Buscando pessoa por CPF: {}", cpf);
        return pessoaRepository.findByCpf(cpf);
    }

    public Pessoa update(UUID id, Pessoa pessoa) {
        logger.info("Atualizando pessoa ID: {}", id);

        return pessoaRepository.findById(id).map(p -> {
            p.setName(pessoa.getName());
            p.setCpf(pessoa.getCpf());
            p.setAge(pessoa.getAge());
            return pessoaRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }

    public void delete(UUID id) {
        logger.info("Deletando pessoa ID: {}", id);
        pessoaRepository.deleteById(id);
    }
}
