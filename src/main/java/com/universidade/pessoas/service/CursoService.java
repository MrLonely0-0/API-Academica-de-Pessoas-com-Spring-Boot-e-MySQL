package com.universidade.pessoas.service;

import com.universidade.pessoas.dto.CursoDTO;
import com.universidade.pessoas.model.Curso;
import com.universidade.pessoas.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CursoService {

    private final CursoRepository cursoRepository;

    public Curso criarCurso(CursoDTO dto) {
        log.info("Criando curso: {}", dto.getNome());
        return cursoRepository.save(Curso.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .build());
    }

    public List<Curso> listarCursos() {
        log.info("Listando todos os cursos");
        return cursoRepository.findAll();
    }

    public Curso buscarPorNome(String nome) {
        log.info("Buscando curso por nome: {}", nome);
        return cursoRepository.findByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));
    }

    public Curso atualizarCurso(Long id, CursoDTO dto) {
        log.info("Atualizando curso ID: {}", id);
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));
        curso.setNome(dto.getNome());
        curso.setDescricao(dto.getDescricao());
        return cursoRepository.save(curso);
    }

    public void deletarCurso(Long id) {
        log.info("Deletando curso ID: {}", id);
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado"));
        cursoRepository.delete(curso);
    }
}
