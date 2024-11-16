package com.example.Ac2_project.controller;

import com.example.Ac2_project.entity.aluno.Aluno;
import com.example.Ac2_project.entity.plataforma.PlataformaCursos;
import com.example.Ac2_project.service.PlataformaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plataformas")
public class PlataformaController {

    @Autowired
    private PlataformaService plataformaService;

    // Cria uma nova plataforma
    @PostMapping
    public ResponseEntity<PlataformaCursos> criarPlataforma(@RequestBody PlataformaCursos plataforma) {
        PlataformaCursos novaPlataforma = plataformaService.salvarPlataforma(plataforma);
        return ResponseEntity.ok(novaPlataforma);
    }

    // Atualiza uma plataforma existente
    @PutMapping("/{id}")
    public ResponseEntity<PlataformaCursos> atualizarPlataforma(@PathVariable String id, @RequestBody PlataformaCursos plataformaAtualizada) {
        PlataformaCursos plataforma = plataformaService.atualizarPlataforma(id, plataformaAtualizada);
        if (plataforma != null) {
            return ResponseEntity.ok(plataforma);
        }
        return ResponseEntity.notFound().build();
    }

    // Busca uma plataforma por ID
    @GetMapping("/{id}")
    public ResponseEntity<PlataformaCursos> buscarPlataformaPorId(@PathVariable String id) {
        PlataformaCursos plataforma = plataformaService.buscarPorId(id);
        if (plataforma != null) {
            return ResponseEntity.ok(plataforma);
        }
        return ResponseEntity.notFound().build();
    }

    // Deleta uma plataforma por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlataforma(@PathVariable String id) {
        plataformaService.deletarPlataforma(id);
        return ResponseEntity.noContent().build();
    }

    // Lista todas as plataformas
    @GetMapping
    public ResponseEntity<List<PlataformaCursos>> listarPlataformas() {
        List<PlataformaCursos> plataformas = plataformaService.listarTodas();
        return ResponseEntity.ok(plataformas);
    }
    // Adiciona um aluno a uma plataforma
    @PostMapping("/{id}/alunos")
    public ResponseEntity<PlataformaCursos> adicionarAluno(@PathVariable String id, @RequestBody Aluno aluno) {
        PlataformaCursos plataforma = plataformaService.adicionarAluno(id, aluno);
        if (plataforma != null) {
            return ResponseEntity.ok(plataforma);
        }
        return ResponseEntity.notFound().build();
    }

    // Adiciona um curso para um aluno específico na plataforma
    @PostMapping("/{id}/alunos/{nomeAluno}/cursos")
    public ResponseEntity<Void> adicionarCursoParaAluno(@PathVariable String id, @PathVariable String nomeAluno, @RequestParam String nomeCurso) {
        plataformaService.adicionarCursoParaAluno(id, nomeAluno, nomeCurso);
        return ResponseEntity.ok().build();
    }

    // Marca a conclusão de um curso para um aluno específico
    @PostMapping("/{id}/alunos/{nomeAluno}/cursos/{nomeCurso}/concluir")
    public ResponseEntity<Void> alunoConcluiCurso(@PathVariable String id, @PathVariable String nomeAluno, @PathVariable String nomeCurso, @RequestParam double notaFinal) {
        plataformaService.alunoConcluiCurso(id, nomeAluno, nomeCurso, notaFinal);
        return ResponseEntity.ok().build();
    }
}
