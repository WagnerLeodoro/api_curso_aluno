package com.wagnerleodoro.api.repository;

import com.wagnerleodoro.api.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
    Optional<Aluno> findAlunoByNome(String nomeAluno);
}
