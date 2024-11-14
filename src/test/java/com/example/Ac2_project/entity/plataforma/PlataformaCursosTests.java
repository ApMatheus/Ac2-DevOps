package com.example.Ac2_project.entity.plataforma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Ac2_project.entity.aluno.Aluno;

import static org.junit.jupiter.api.Assertions.*;

class PlataformaCursosTests {

  private PlataformaCursos plataforma;

  @BeforeEach
  void setUp() {
    plataforma = new PlataformaCursos();
    Aluno aluno = new Aluno("Maria", "senha123");
    plataforma.adicionarAluno(aluno);
  }

  @Test
  void testAutenticarAlunoComSucesso() {
    assertTrue(plataforma.autenticarAluno("Maria", "senha123"));
  }

  @Test
  void testFalhaAutenticacaoAluno() {
    assertFalse(plataforma.autenticarAluno("Maria", "senhaErrada"));
  }
}
