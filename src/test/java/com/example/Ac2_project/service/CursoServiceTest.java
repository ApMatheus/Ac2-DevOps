package com.example.Ac2_project.service;

import com.example.Ac2_project.entity.curso.Curso;
import com.example.Ac2_project.repository.Curso_Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {

  @Mock
  private Curso_Repository cursoRepository;

  @InjectMocks
  private CursoService cursoService;

  private Curso curso;
  private Curso cursoAtualizado;

  @BeforeEach
  public void setUp() {
    curso = new Curso("Matemática");
    cursoAtualizado = new Curso("Matemática Avançada");
  }

  @Test
  public void testSalvarCurso() {
    when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

    Curso cursoSalvo = cursoService.salvarCurso(curso);

    assertNotNull(cursoSalvo);
    assertEquals("Matemática", cursoSalvo.getNome());
    verify(cursoRepository, times(1)).save(curso);
  }

  @Test
  public void testAtualizarCurso_Existente() {
    when(cursoRepository.findById("1")).thenReturn(Optional.of(curso));
    when(cursoRepository.save(any(Curso.class))).thenReturn(cursoAtualizado);

    Curso cursoAtualizadoResult = cursoService.atualizarCurso("1", cursoAtualizado);

    assertNotNull(cursoAtualizadoResult);
    assertEquals("Matemática Avançada", cursoAtualizadoResult.getNome());

    verify(cursoRepository, times(1)).findById("1");
    verify(cursoRepository, times(1)).save(cursoAtualizado);
  }

  @Test
  public void testAtualizarCurso_NaoExistente() {
    when(cursoRepository.findById("1")).thenReturn(Optional.empty());

    Curso cursoAtualizadoResult = cursoService.atualizarCurso("1", cursoAtualizado);

    assertNull(cursoAtualizadoResult);
    verify(cursoRepository, times(1)).findById("1");
    verify(cursoRepository, times(0)).save(any(Curso.class));
  }

  @Test
  public void testBuscarCursoPorId_Encontrado() {
    when(cursoRepository.findById("1")).thenReturn(Optional.of(curso));

    Curso cursoEncontrado = cursoService.buscarPorId("1");

    assertNotNull(cursoEncontrado);
    assertEquals("Matemática", cursoEncontrado.getNome());

    verify(cursoRepository, times(1)).findById("1");
  }

  @Test
  public void testBuscarCursoPorId_NaoEncontrado() {
    when(cursoRepository.findById("1")).thenReturn(Optional.empty());

    Curso cursoEncontrado = cursoService.buscarPorId("1");

    assertNull(cursoEncontrado);
    verify(cursoRepository, times(1)).findById("1");
  }

  @Test
  public void testDeletarCurso() {
    doNothing().when(cursoRepository).deleteById("1");

    cursoService.deletarCurso("1");

    verify(cursoRepository, times(1)).deleteById("1");
  }

  @Test
  public void testListarTodosCursos() {
    when(cursoRepository.findAll()).thenReturn(Arrays.asList(curso));

    var cursos = cursoService.listarTodos();

    assertNotNull(cursos);
    assertFalse(cursos.isEmpty());
    assertEquals(1, cursos.size());
    assertEquals("Matemática", cursos.get(0).getNome());

    verify(cursoRepository, times(1)).findAll();
  }
}
