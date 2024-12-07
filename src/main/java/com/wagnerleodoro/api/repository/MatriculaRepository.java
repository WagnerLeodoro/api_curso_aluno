package com.wagnerleodoro.api.repository;

import com.wagnerleodoro.api.model.Aluno;
import com.wagnerleodoro.api.model.Curso;
import com.wagnerleodoro.api.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    Optional<Matricula> findMatriculaByAlunoAndCurso(Aluno aluno, Curso curso);
}
