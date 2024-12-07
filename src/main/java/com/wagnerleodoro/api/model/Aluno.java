package com.wagnerleodoro.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tb_aluno")
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aluno")
    private Integer id;

    @Column(name = "nome_aluno", length = 100)
    private String nome;

    @Column(name = "email_aluno", length = 150)
    private String email;

    @Column(name = "cpf_aluno", length = 14)
    private String cpf;

    @Column(name = "data_aluno")
    private LocalDate dataNascimento;
}