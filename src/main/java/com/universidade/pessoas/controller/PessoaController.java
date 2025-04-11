package com.universidade.pessoas.controller;

import com.universidade.pessoas.dto.PessoaDTO;
import com.universidade.pessoas.model.Pessoa;
import com.universidade.pessoas.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/pessoas/universidade")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@RequestBody PessoaDTO pessoaDto)
    {
       
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDto, pessoa);

        Pessoa savedPessoa = pessoaRepository.save(pessoa);

        System.out.println("Pessoa salva: " + savedPessoa);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPessoa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPessoaById(@PathVariable UUID id) 
    {
        Optional<Pessoa> foundPessoa = pessoaRepository.findById(id);

        if (foundPessoa.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa Not Found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(foundPessoa.get());
        }
    }
}
