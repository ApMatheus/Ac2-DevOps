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

import com.example.Ac2_project.entity.aluno.Aluno;

@SpringBootTest
class AlunoRepositoryTests {

    @Autowired
    private Aluno_Repository alunoRepository;

    private Aluno aluno;

    @BeforeEach
    void setUp() {
        aluno = new Aluno("João", "12345");
        aluno.setMoedas(5);
    }

    @Test
    @Rollback(false)
    void testSaveAluno() {
        Aluno savedAluno = alunoRepository.save(aluno);
        assertNotNull(savedAluno);
        assertEquals("João", savedAluno.getNome());
    }

    @Test
    void testFindById() {
        alunoRepository.save(aluno);
        Optional<Aluno> foundAluno = alunoRepository.findById(aluno.getId());
        assertTrue(foundAluno.isPresent());
        assertEquals("João", foundAluno.get().getNome());
    }

    @Test
    @Rollback(false)
    void testDeleteAluno() {
        alunoRepository.save(aluno);
        alunoRepository.delete(aluno);
        Optional<Aluno> deletedAluno = alunoRepository.findById(aluno.getId());
        assertFalse(deletedAluno.isPresent());
    }
}
