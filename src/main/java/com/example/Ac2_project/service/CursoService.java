package com.example.Ac2_project.service;

import com.example.Ac2_project.entity.curso.Curso;
import com.example.Ac2_project.repository.Curso_Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

  @Autowired
  private Curso_Repository cursoRepository;

  // Salva um novo curso
  public Curso salvarCurso(Curso curso) {
    return cursoRepository.save(curso);
  }

  // Atualiza um curso existente
  public Curso atualizarCurso(String id, Curso cursoAtualizado) {
    Optional<Curso> cursoExistente = cursoRepository.findById(id);
    if (cursoExistente.isPresent()) {
      Curso curso = cursoExistente.get();
      curso.setNome(cursoAtualizado.getNome());
      curso.setNotaFinal(cursoAtualizado.getNotaFinal());
      curso.setConcluido(cursoAtualizado.isConcluido());
      return cursoRepository.save(curso);
    }
    return null;
  }

  // Busca um curso por ID
  public Curso buscarPorId(String id) {
    return cursoRepository.findById(id).orElse(null);
  }

  // Deleta um curso por ID
  public void deletarCurso(String id) {
    cursoRepository.deleteById(id);
  }

  // Lista todos os cursos
  public List<Curso> listarTodos() {
    return cursoRepository.findAll();
  }
}
