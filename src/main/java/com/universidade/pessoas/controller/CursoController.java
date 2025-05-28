package com.universidade.controller;

import com.universidade.dto.CursoDTO;
import com.universidade.model.Curso;
import com.universidade.service.CursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @PostMapping
    public ResponseEntity<Curso> criar(@RequestBody @Valid CursoDTO dto) {
        return ResponseEntity.ok(cursoService.criarCurso(dto));
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        return ResponseEntity.ok(cursoService.listarCursos());
    }

    @GetMapping("/buscar")
    public ResponseEntity<Curso> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(cursoService.buscarPorNome(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable Long id, @RequestBody @Valid CursoDTO dto) {
        return ResponseEntity.ok(cursoService.atualizarCurso(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cursoService.deletarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
