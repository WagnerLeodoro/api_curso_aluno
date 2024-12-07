package com.wagnerleodoro.api.repository;

import com.wagnerleodoro.api.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
    public Optional<Curso> findCursoByNome(String nome);
}
