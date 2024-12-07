package com.wagnerleodoro.api.rest;

import com.wagnerleodoro.api.model.Curso;
import com.wagnerleodoro.api.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/cursos")
public class CursoRest {
    @Autowired
    private CursoRepository cursoRepository;

    public static List<Curso> lista = new ArrayList<Curso>();

    @GetMapping("/")
    public ResponseEntity<List<Curso>> getCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable int id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        return curso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/")
    public ResponseEntity<Curso> addCurso(@RequestBody Curso curso) {
        Optional<Curso> op = cursoRepository.findCursoByNome(curso.getNome());
        if (op.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(op.get());
        } else {
            curso.setId(-1);
            cursoRepository.save(curso);
            return ResponseEntity.status(HttpStatus.CREATED).body(curso);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCurso(@PathVariable int id, @RequestBody Curso curso) {
        Optional<Curso> op = cursoRepository.findById(id);
        if(op.isPresent()) {
            curso.setId(id);
            cursoRepository.save(curso);
            return ResponseEntity.ok(curso);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCurso(@PathVariable int id) {
        Optional<Curso> op = cursoRepository.findById(id);
        if(op.isPresent()) {
            cursoRepository.delete(op.get());
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
