package com.universidade.pessoas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "universidade")
@Data
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String cpf;

    private int age;

    @Override
    public String toString(){
        return "Pessoa{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
