package com.example.Ac2_project.entity.aluno;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.Ac2_project.entity.curso.Curso;

import static org.junit.jupiter.api.Assertions.*;

class AlunoTests {

    private Aluno aluno;

    @BeforeEach
    void setUp() {
        aluno = new Aluno("João", "12345");
    }

    @Test
    void testAutenticacaoCorreta() {
        assertTrue(aluno.autenticar("12345"));
    }

    @Test
    void testAutenticacaoIncorreta() {
        assertFalse(aluno.autenticar("senhaErrada"));
    }

    @Test
    void testAdicionarCurso() {
        Curso curso = new Curso("Matemática");
        aluno.adicionarCurso(curso);
        assertEquals(1, aluno.getCursos().size());
        assertEquals("Matemática", aluno.getCursos().get(0).getNome());
    }

    @Test
    void testConcluirCurso() {
        Curso curso = new Curso("História");
        aluno.adicionarCurso(curso);
        aluno.concluirCurso("História", 8.0);

        Curso cursoConcluido = aluno.getCursos().get(0);
        assertTrue(cursoConcluido.isConcluido());
        assertEquals(8.0, cursoConcluido.getNotaFinal());
    }

    @Test
    void testVerificacaoPremium() {
        for (int i = 1; i <= 12; i++) {
            Curso curso = new Curso("Curso " + i);
            aluno.adicionarCurso(curso);
            aluno.concluirCurso("Curso " + i, 8.0);
        }
        assertTrue(aluno.isPremium());
        assertEquals(3, aluno.getMoedas());
    }
}
