package com.example.Ac2_project.entity.curso;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CursoTests {

    @Test
    void testConclusaoDeCursoComNotaAprovada() {
        Curso curso = new Curso("Português");
        assertTrue(curso.concluirCurso(8.5));
        assertTrue(curso.isConcluido());
        assertEquals(8.5, curso.getNotaFinal());
    }

    @Test
    void testConclusaoDeCursoComNotaReprovada() {
        Curso curso = new Curso("Ciências");
        assertFalse(curso.concluirCurso(5.0));
        assertFalse(curso.isConcluido());
        assertEquals(5.0, curso.getNotaFinal());
    }
}
