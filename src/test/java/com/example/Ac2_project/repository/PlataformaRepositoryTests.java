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

import com.example.Ac2_project.entity.plataforma.PlataformaCursos;

@SpringBootTest
class PlataformaRepositoryTests {

    @Autowired
    private Plataforma_Repository plataformaRepository;

    private PlataformaCursos plataforma;

    @BeforeEach
    void setUp() {
        plataforma = PlataformaCursos.builder()
            .nome("Plataforma Exemplo")
            .build();
    }

    @Test
    @Rollback(false)
    void testSavePlataforma() {
        PlataformaCursos savedPlataforma = plataformaRepository.save(plataforma);
        assertNotNull(savedPlataforma);
        assertEquals("Plataforma Exemplo", savedPlataforma.getNome());
    }

    @Test
    void testFindById() {
        PlataformaCursos savedPlataforma = plataformaRepository.save(plataforma);
        Optional<PlataformaCursos> foundPlataforma = plataformaRepository.findById(savedPlataforma.getId());
        assertTrue(foundPlataforma.isPresent());
        assertEquals("Plataforma Exemplo", foundPlataforma.get().getNome());
    }

    @Test
    @Rollback(false)
    void testDeletePlataforma() {
        PlataformaCursos savedPlataforma = plataformaRepository.save(plataforma);
        plataformaRepository.delete(savedPlataforma);
        Optional<PlataformaCursos> deletedPlataforma = plataformaRepository.findById(savedPlataforma.getId());
        assertFalse(deletedPlataforma.isPresent());
    }
}
