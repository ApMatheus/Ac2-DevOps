package com.example.Ac2_project.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.example.Ac2_project.entity.curso.Curso;

@SpringBootTest
class CursoRepositoryTests {

  @Autowired
  private Curso_Repository cursoRepository;

  private Curso curso;

  @BeforeEach
  void setUp() {
    curso = new Curso("Matemática");
  }

  @Test
  @Rollback(false)
  void testSaveCurso() {
    Curso savedCurso = cursoRepository.save(curso);
    assertNotNull(savedCurso);
    assertEquals("Matemática", savedCurso.getNome());
  }

  @Test
  void testFindById() {
    cursoRepository.save(curso);
    Optional<Curso> foundCurso = cursoRepository.findById(curso.getId());
    assertTrue(foundCurso.isPresent());
    assertEquals("Matemática", foundCurso.get().getNome());
  }

  @Test
  @Rollback(false)
  void testDeleteCurso() {
    cursoRepository.save(curso);
    cursoRepository.delete(curso);
    Optional<Curso> deletedCurso = cursoRepository.findById(curso.getId());
    assertFalse(deletedCurso.isPresent());
  }
}
