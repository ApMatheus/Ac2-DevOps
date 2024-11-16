package com.example.Ac2_project.service;

import com.example.Ac2_project.entity.aluno.Aluno;
import com.example.Ac2_project.entity.curso.Curso;
import com.example.Ac2_project.repository.Aluno_Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlunoServiceTest {

  @Mock
  private Aluno_Repository alunoRepository;

  @InjectMocks
  private AlunoService alunoService;

  private Aluno aluno;
  private Aluno alunoAtualizado;

  @BeforeEach
  public void setUp() {
    aluno = new Aluno("João", "senha123");
    alunoAtualizado = new Aluno("João Silva", "novaSenha123");
  }

  @Test
  public void testSalvarAluno() {
    when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);

    Aluno alunoSalvo = alunoService.salvarAluno(aluno);

    assertNotNull(alunoSalvo);
    assertEquals("João", alunoSalvo.getNome());
    verify(alunoRepository, times(1)).save(aluno);
  }

  @Test
  public void testAtualizarAluno_NaoExistente() {
    when(alunoRepository.findById("1")).thenReturn(Optional.empty());

    Aluno alunoAtualizadoResult = alunoService.atualizarAluno("1", alunoAtualizado);

    assertNull(alunoAtualizadoResult);
    verify(alunoRepository, times(1)).findById("1");
    verify(alunoRepository, times(0)).save(any(Aluno.class));
  }

  @Test
  public void testBuscarAlunoPorId_Encontrado() {
    when(alunoRepository.findById("1")).thenReturn(Optional.of(aluno));

    Aluno alunoEncontrado = alunoService.buscarPorId("1");

    assertNotNull(alunoEncontrado);
    assertEquals("João", alunoEncontrado.getNome());

    verify(alunoRepository, times(1)).findById("1");
  }

  @Test
  public void testBuscarAlunoPorId_NaoEncontrado() {
    when(alunoRepository.findById("1")).thenReturn(Optional.empty());

    Aluno alunoEncontrado = alunoService.buscarPorId("1");

    assertNull(alunoEncontrado);
    verify(alunoRepository, times(1)).findById("1");
  }

  @Test
  public void testDeletarAluno() {
    doNothing().when(alunoRepository).deleteById("1");

    alunoService.deletarAluno("1");

    verify(alunoRepository, times(1)).deleteById("1");
  }

  @Test
  public void testListarTodosAlunos() {
    when(alunoRepository.findAll()).thenReturn(Arrays.asList(aluno));

    var alunos = alunoService.listarTodos();

    assertNotNull(alunos);
    assertFalse(alunos.isEmpty());
    assertEquals(1, alunos.size());
    assertEquals("João", alunos.get(0).getNome());

    verify(alunoRepository, times(1)).findAll();
  }

  @Test
  public void testAdicionarCurso() {
    Curso curso = new Curso("Curso 1");
    aluno.adicionarCurso(curso);

    when(alunoRepository.findById("1")).thenReturn(Optional.of(aluno));
    when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);

    Aluno alunoComCurso = alunoService.adicionarCurso("1", curso);

    assertNotNull(alunoComCurso);
    assertTrue(alunoComCurso.getCursos().contains(curso));

    verify(alunoRepository, times(1)).findById("1");
    verify(alunoRepository, times(1)).save(aluno);
  }

  @Test
  public void testAdicionarCurso_AlunoNaoEncontrado() {
    Curso curso = new Curso("Curso 1");

    when(alunoRepository.findById("1")).thenReturn(Optional.empty());

    Aluno alunoComCurso = alunoService.adicionarCurso("1", curso);

    assertNull(alunoComCurso);
    verify(alunoRepository, times(1)).findById("1");
  }

  @Test
  public void testConcluirCurso() {
    Curso curso = new Curso("Curso 1");
    aluno.adicionarCurso(curso);
    aluno.concluirCurso("Curso 1", 9.5);

    when(alunoRepository.findById("1")).thenReturn(Optional.of(aluno));
    when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);

    Aluno alunoComCursoConcluido = alunoService.concluirCurso("1", "Curso 1", 9.5);

    assertNotNull(alunoComCursoConcluido);
    assertTrue(alunoComCursoConcluido.getCursos().get(0).isConcluido());
    assertEquals(9.5, alunoComCursoConcluido.getCursos().get(0).getNotaFinal());

    verify(alunoRepository, times(1)).findById("1");
    verify(alunoRepository, times(1)).save(aluno);
  }

  @Test
  public void testConcluirCurso_AlunoNaoEncontrado() {
    when(alunoRepository.findById("1")).thenReturn(Optional.empty());

    Aluno alunoComCursoConcluido = alunoService.concluirCurso("1", "Curso 1", 9.5);

    assertNull(alunoComCursoConcluido);
    verify(alunoRepository, times(1)).findById("1");
  }
}
