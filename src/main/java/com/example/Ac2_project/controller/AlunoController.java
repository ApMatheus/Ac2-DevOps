package com.example.Ac2_project.controller;

import com.example.Ac2_project.entity.aluno.Aluno;
import com.example.Ac2_project.entity.curso.Curso;
import com.example.Ac2_project.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

  @Autowired
  private AlunoService alunoService;

  // Cria um novo aluno
  @PostMapping
  public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
    Aluno novoAluno = alunoService.salvarAluno(aluno);
    return ResponseEntity.ok(novoAluno);
  }

  // Atualiza um aluno existente
  @PutMapping("/{id}")
  public ResponseEntity<Aluno> atualizarAluno(@PathVariable String id, @RequestBody Aluno alunoAtualizado) {
    Aluno aluno = alunoService.atualizarAluno(id, alunoAtualizado);
    if (aluno != null) {
      return ResponseEntity.ok(aluno);
    }
    return ResponseEntity.notFound().build();
  }

  // Busca um aluno por ID
  @GetMapping("/{id}")
  public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable String id) {
    Aluno aluno = alunoService.buscarPorId(id);
    if (aluno != null) {
      return ResponseEntity.ok(aluno);
    }
    return ResponseEntity.notFound().build();
  }

  // Deleta um aluno por ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarAluno(@PathVariable String id) {
    alunoService.deletarAluno(id);
    return ResponseEntity.noContent().build();
  }

  // Lista todos os alunos
  @GetMapping
  public ResponseEntity<List<Aluno>> listarAlunos() {
    List<Aluno> alunos = alunoService.listarTodos();
    return ResponseEntity.ok(alunos);
  }

  // Adiciona um curso a um aluno
  @PostMapping("/{alunoId}/cursos")
  public ResponseEntity<Aluno> adicionarCursoAoAluno(@PathVariable String alunoId, @RequestBody Curso curso) {
    Aluno aluno = alunoService.adicionarCurso(alunoId, curso);
    if (aluno != null) {
      return ResponseEntity.ok(aluno);
    }
    return ResponseEntity.notFound().build();
  }

  // Marca a conclusão de um curso para um aluno específico
  @PostMapping("/{alunoId}/cursos/{nomeCurso}/concluir")
  public ResponseEntity<Aluno> concluirCurso(@PathVariable String alunoId, @PathVariable String nomeCurso,
      @RequestParam double notaFinal) {
    Aluno aluno = alunoService.concluirCurso(alunoId, nomeCurso, notaFinal);
    if (aluno != null) {
      return ResponseEntity.ok(aluno);
    }
    return ResponseEntity.notFound().build();
  }
}
