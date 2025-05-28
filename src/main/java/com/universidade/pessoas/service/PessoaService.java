package com.universidade.service;

import com.universidade.dto.PessoaDTO;
import com.universidade.model.Curso;
import com.universidade.model.Pessoa;
import com.universidade.repository.CursoRepository;
import com.universidade.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final CursoRepository cursoRepository;

    public Pessoa criarPessoa(PessoaDTO dto) {
        log.info("Criando pessoa: {}", dto.getNome());
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));

        Pessoa pessoa = Pessoa.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .curso(curso)
                .build();

        return pessoaRepository.save(pessoa);
    }

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
    }

    public Pessoa atualizarPessoa(Long id, PessoaDTO dto) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));

        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setEmail(dto.getEmail());
        pessoa.setCurso(curso);

        return pessoaRepository.save(pessoa);
    }

    public void deletarPessoa(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

        pessoaRepository.delete(pessoa);
    }
}
