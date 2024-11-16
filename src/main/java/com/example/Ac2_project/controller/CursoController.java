package com.example.Ac2_project.controller;

import com.example.Ac2_project.entity.curso.Curso;
import com.example.Ac2_project.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

  @Autowired
  private CursoService cursoService;

  // Cria um novo curso
  @PostMapping
  public ResponseEntity<Curso> criarCurso(@RequestBody Curso curso) {
    Curso novoCurso = cursoService.salvarCurso(curso);
    return ResponseEntity.ok(novoCurso);
  }

  // Atualiza um curso existente
  @PutMapping("/{id}")
  public ResponseEntity<Curso> atualizarCurso(@PathVariable String id, @RequestBody Curso cursoAtualizado) {
    Curso curso = cursoService.atualizarCurso(id, cursoAtualizado);
    if (curso != null) {
      return ResponseEntity.ok(curso);
    }
    return ResponseEntity.notFound().build();
  }

  // Busca um curso por ID
  @GetMapping("/{id}")
  public ResponseEntity<Curso> buscarCursoPorId(@PathVariable String id) {
    Curso curso = cursoService.buscarPorId(id);
    if (curso != null) {
      return ResponseEntity.ok(curso);
    }
    return ResponseEntity.notFound().build();
  }

  // Deleta um curso por ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarCurso(@PathVariable String id) {
    cursoService.deletarCurso(id);
    return ResponseEntity.noContent().build();
  }

  // Lista todos os cursos
  @GetMapping
  public ResponseEntity<List<Curso>> listarCursos() {
    List<Curso> cursos = cursoService.listarTodos();
    return ResponseEntity.ok(cursos);
  }
}
