package com.example.Ac2_project.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.Ac2_project.controller.AlunoController;
import com.example.Ac2_project.entity.aluno.Aluno;
import com.example.Ac2_project.entity.curso.Curso;
import com.example.Ac2_project.service.AlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AlunoService alunoService;

  @Autowired
  private ObjectMapper objectMapper;

  private Aluno aluno;
  private Curso curso;

  @BeforeEach
  public void setup() {
    aluno = new Aluno("João", "senha123");
    aluno.setId("1");
    aluno.setMoedas(5);
    aluno.setPremium(true);

    curso = new Curso("Matemática");
    curso.setId("101");
  }

  @Test
  public void deveCriarAluno() throws Exception {
    when(alunoService.salvarAluno(any(Aluno.class))).thenReturn(aluno);

    mockMvc.perform(post("/api/alunos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(aluno)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value(aluno.getNome()));
  }

  @Test
  public void deveAtualizarAluno() throws Exception {
    when(alunoService.atualizarAluno(anyString(), any(Aluno.class))).thenReturn(aluno);

    mockMvc.perform(put("/api/alunos/{id}", "1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(aluno)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value(aluno.getNome()));
  }

  @Test
  public void deveBuscarAlunoPorId() throws Exception {
    when(alunoService.buscarPorId("1")).thenReturn(aluno);

    mockMvc.perform(get("/api/alunos/{id}", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value(aluno.getNome()));
  }

  @Test
  public void deveRetornarNotFoundAoBuscarAlunoInexistente() throws Exception {
    when(alunoService.buscarPorId("2")).thenReturn(null);

    mockMvc.perform(get("/api/alunos/{id}", "2"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void deveDeletarAluno() throws Exception {
    mockMvc.perform(delete("/api/alunos/{id}", "1"))
        .andExpect(status().isNoContent());
  }

  @Test
  public void deveListarTodosOsAlunos() throws Exception {
    List<Aluno> alunos = new ArrayList<>();
    alunos.add(aluno);
    when(alunoService.listarTodos()).thenReturn(alunos);

    mockMvc.perform(get("/api/alunos"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].nome").value(aluno.getNome()));
  }

  @Test
  public void deveAdicionarCursoAoAluno() throws Exception {
    when(alunoService.adicionarCurso(anyString(), any(Curso.class))).thenReturn(aluno);

    mockMvc.perform(post("/api/alunos/{alunoId}/cursos", "1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(curso)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value(aluno.getNome()));
  }

  @Test
  public void deveConcluirCursoParaAluno() throws Exception {
    when(alunoService.concluirCurso(anyString(), anyString(), any(double.class))).thenReturn(aluno);

    mockMvc.perform(post("/api/alunos/{alunoId}/cursos/{nomeCurso}/concluir", "1", "Matemática")
        .param("notaFinal", "8.5"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value(aluno.getNome()));
  }
}
