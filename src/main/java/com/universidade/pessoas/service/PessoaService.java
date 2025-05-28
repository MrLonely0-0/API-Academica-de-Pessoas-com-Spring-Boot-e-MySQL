package com.universidade.pessoa.service;

import com.universidade.pessoa.dto.PessoaDTO;
import com.universidade.pessoa.model.Pessoa;
import com.universidade.pessoa.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

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

    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPorId(UUID id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
    }

    public Pessoa buscarPorCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf).orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
    }

    public Pessoa atualizar(UUID id, PessoaDTO dto) {
        Pessoa pessoa = buscarPorId(id);
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setIdade(dto.getIdade());
        return pessoaRepository.save(pessoa);
    }

    public void deletar(UUID id) {
        Pessoa pessoa = buscarPorId(id);
        pessoaRepository.delete(pessoa);
    }
}
