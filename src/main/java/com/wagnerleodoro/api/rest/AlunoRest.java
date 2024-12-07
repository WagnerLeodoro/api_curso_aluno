package com.wagnerleodoro.api.rest;

import com.wagnerleodoro.api.model.Aluno;
import com.wagnerleodoro.api.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/alunos")
public class AlunoRest {
    @Autowired
    private AlunoRepository alunoRepository;
    public static List<Aluno> alunos = new ArrayList<Aluno>();

    @GetMapping("/")
    public ResponseEntity<List<Aluno>> getAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable int id) {
        Optional<Aluno> op = alunoRepository.findById(id);
        return op.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Aluno> postAluno(@RequestBody Aluno aluno) {
        Optional<Aluno> op = alunoRepository.findAlunoByNome(aluno.getNome());
        if(op.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            aluno.setId(-1);
            alunoRepository.save(aluno);
            return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAluno(@PathVariable("id") int id, @RequestBody Aluno aluno) {
        Optional<Aluno> op = alunoRepository.findById(id);
        if(op.isPresent()) {
            aluno.setId(id);
            alunoRepository.save(aluno);
            return ResponseEntity.ok(aluno);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> deleteAluno(@PathVariable("id") int id) {
        Optional<Aluno> op = alunoRepository.findById(id);
        if(op.isPresent()) {
            alunoRepository.delete(op.get());
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
