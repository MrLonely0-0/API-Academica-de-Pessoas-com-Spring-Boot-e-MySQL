package com.universidade.pessoas.service;

import com.universidade.pessoas.dto.PessoaDTO;
import com.universidade.pessoas.model.Pessoa;
import com.universidade.pessoas.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa salvar(PessoaDTO dto) {
        if (pessoaRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setIdade(dto.getIdade());

        log.info("Salvando pessoa: {}", pessoa);
        return pessoaRepository.save(pessoa);
    }

    @Transactional(readOnly = true)
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Pessoa buscarPorId(UUID id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
    }

    @Transactional(readOnly = true)
    public Pessoa buscarPorCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
    }

    public Pessoa atualizar(UUID id, Pessoa dto) {
        Pessoa pessoa = buscarPorId(id);


        if (!pessoa.getCpf().equals(dto.getCpf()) &&
                pessoaRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setIdade(dto.getIdade());

        log.info("Atualizando pessoa ID {}: {}", id, pessoa);
        return pessoaRepository.save(pessoa);
    }

    public void deletar(UUID id) {
        Pessoa pessoa = buscarPorId(id);
        log.info("Deletando pessoa ID: {}", id);
        pessoaRepository.delete(pessoa);
    }
}