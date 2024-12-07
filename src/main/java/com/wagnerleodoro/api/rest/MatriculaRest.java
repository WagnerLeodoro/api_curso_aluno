package com.wagnerleodoro.api.rest;

import com.wagnerleodoro.api.model.Matricula;
import com.wagnerleodoro.api.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("matriculas")
public class MatriculaRest {
    @Autowired
    private MatriculaRepository matriculaRepository;

    @GetMapping("/")
    public ResponseEntity<List<Matricula>> getMatriculas() {
        List<Matricula> lista = matriculaRepository.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> getMatriculaById(@PathVariable int id) {
        Optional<Matricula> op = matriculaRepository.findById(id);
        return op.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/")
    public ResponseEntity<Matricula> addMatricula(@RequestBody Matricula matricula) {
        Optional<Matricula> op = matriculaRepository.findMatriculaByAlunoAndCurso(matricula.getAluno(), matricula.getCurso());
        if(op.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            matriculaRepository.save(matricula);
            return ResponseEntity.status(HttpStatus.CREATED).body(matricula);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matricula> updateMatricula(@PathVariable int id, @RequestBody Matricula matricula) {
        Optional<Matricula> op = matriculaRepository.findById(id);
        Optional<Matricula> matOp = matriculaRepository.findMatriculaByAlunoAndCurso(matricula.getAluno(), matricula.getCurso());
        if(matOp.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else if(op.isPresent()) {
            matricula.setId(id);
            matriculaRepository.save(matricula);
            return ResponseEntity.ok(matricula);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Matricula> deleteMatricula(@PathVariable int id) {
        Optional<Matricula> op = matriculaRepository.findById(id);
        if(op.isPresent()) {
            matriculaRepository.delete(op.get());
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
