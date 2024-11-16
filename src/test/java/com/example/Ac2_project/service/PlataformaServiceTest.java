package com.example.Ac2_project.service;

import com.example.Ac2_project.entity.aluno.Aluno;
import com.example.Ac2_project.entity.curso.Curso;
import com.example.Ac2_project.entity.plataforma.PlataformaCursos;
import com.example.Ac2_project.repository.Plataforma_Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlataformaServiceTest {

  @Mock
  private Plataforma_Repository plataformaRepository;

  @InjectMocks
  private PlataformaService plataformaService;

  private PlataformaCursos plataforma;
  private Aluno aluno;
  private Curso curso;

  @BeforeEach
  public void setUp() {
    plataforma = new PlataformaCursos();
    aluno = new Aluno("João", "senha123");
    curso = new Curso("Matemática");
  }

  @Test
  public void testAtualizarPlataforma_NaoExistente() {
    when(plataformaRepository.findById("1")).thenReturn(Optional.empty());

    PlataformaCursos plataformaResult = plataformaService.atualizarPlataforma("1", plataforma);

    assertNull(plataformaResult);
    verify(plataformaRepository, times(1)).findById("1");
    verify(plataformaRepository, times(0)).save(any(PlataformaCursos.class));
  }

  @Test
  public void testBuscarPlataformaPorId_NaoEncontrado() {
    when(plataformaRepository.findById("1")).thenReturn(Optional.empty());

    PlataformaCursos plataformaEncontrada = plataformaService.buscarPorId("1");

    assertNull(plataformaEncontrada);
    verify(plataformaRepository, times(1)).findById("1");
  }

  @Test
  public void testDeletarPlataforma() {
    doNothing().when(plataformaRepository).deleteById("1");

    plataformaService.deletarPlataforma("1");

    verify(plataformaRepository, times(1)).deleteById("1");
  }

  @Test
  public void testAdicionarAluno() {
    when(plataformaRepository.findById("1")).thenReturn(Optional.of(plataforma));
    when(plataformaRepository.save(any(PlataformaCursos.class))).thenReturn(plataforma);

    PlataformaCursos plataformaAtualizada = plataformaService.adicionarAluno("1", aluno);

    assertNotNull(plataformaAtualizada);
    verify(plataformaRepository, times(1)).findById("1");
    verify(plataformaRepository, times(1)).save(plataformaAtualizada);
  }

  @Test
  public void testAdicionarCursoParaAluno() {
    when(plataformaRepository.findById("1")).thenReturn(Optional.of(plataforma));

    plataformaService.adicionarCursoParaAluno("1", aluno.getNome(), curso.getNome());

    verify(plataformaRepository, times(1)).findById("1");
    verify(plataformaRepository, times(1)).save(plataforma);
  }

  @Test
  public void testAlunoConcluiCurso() {
    when(plataformaRepository.findById("1")).thenReturn(Optional.of(plataforma));

    plataformaService.alunoConcluiCurso("1", aluno.getNome(), curso.getNome(), 9.5);

    verify(plataformaRepository, times(1)).findById("1");
    verify(plataformaRepository, times(1)).save(plataforma);
  }
}
