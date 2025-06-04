package com.universidade.pessoas.controller;

import com.universidade.pessoas.model.Pessoa;
import com.universidade.pessoas.dto.PessoaDTO;
import com.universidade.pessoas.service.PessoaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> create(@RequestBody PessoaDTO pessoaDto) {
        return ResponseEntity.ok(pessoaService.salvar(pessoaDto));
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listAll() {
        return ResponseEntity.ok(pessoaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getById(@PathVariable UUID id) {
        Optional<Pessoa> pessoa = Optional.ofNullable(pessoaService.buscarPorId(id));
        return pessoa.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Pessoa> getByCpf(@PathVariable String cpf) {
        Optional<Pessoa> pessoa = Optional.ofNullable(pessoaService.buscarPorCpf(cpf));
        return pessoa.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable UUID id, @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.atualizar(id, pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        pessoaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
