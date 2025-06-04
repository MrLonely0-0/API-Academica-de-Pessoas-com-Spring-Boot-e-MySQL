package com.universidade.pessoas.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import java.util.Set;

@Entity
@Table(name = "pessoas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false)
    private int idade;

    // Relacionamento com Curso (Muitos para Muitos)
    @ManyToMany
    @JoinTable(
        name = "pessoa_curso",
        joinColumns = @JoinColumn(name = "pessoa_id"),
        inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private Set<Curso> cursos;

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", name='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", age=" + idade +
                '}';
    }
}
