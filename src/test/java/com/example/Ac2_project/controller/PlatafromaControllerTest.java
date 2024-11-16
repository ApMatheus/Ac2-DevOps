package com.example.Ac2_project.controller;

import com.example.Ac2_project.controller.PlataformaController;
import com.example.Ac2_project.entity.aluno.Aluno;
import com.example.Ac2_project.entity.plataforma.PlataformaCursos;
import com.example.Ac2_project.service.PlataformaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class PlatafromaControllerTest {

  @Mock
  private PlataformaService plataformaService;

  @InjectMocks
  private PlataformaController plataformaController;

  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(plataformaController).build();
  }

  @Test
  public void testCriarPlataforma() throws Exception {
    // Dado
    PlataformaCursos plataforma = new PlataformaCursos();
    plataforma.setNome("Plataforma 1");
    when(plataformaService.salvarPlataforma(any(PlataformaCursos.class))).thenReturn(plataforma);

    // Quando e então
    mockMvc.perform(post("/api/plataformas")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"nome\":\"Plataforma 1\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value("Plataforma 1"));

    verify(plataformaService, times(1)).salvarPlataforma(any(PlataformaCursos.class));
  }

  @Test
  public void testAtualizarPlataforma() throws Exception {
    // Dado
    PlataformaCursos plataformaExistente = new PlataformaCursos();
    plataformaExistente.setNome("Plataforma 1");
    when(plataformaService.atualizarPlataforma(eq("1"), any(PlataformaCursos.class))).thenReturn(plataformaExistente);

    // Quando e então
    mockMvc.perform(put("/api/plataformas/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"nome\":\"Plataforma 1\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value("Plataforma 1"));

    verify(plataformaService, times(1)).atualizarPlataforma(eq("1"), any(PlataformaCursos.class));
  }

  @Test
  public void testBuscarPlataformaPorId() throws Exception {
    // Dado
    PlataformaCursos plataforma = new PlataformaCursos();
    plataforma.setNome("Plataforma 1");
    when(plataformaService.buscarPorId("1")).thenReturn(plataforma);

    // Quando e então
    mockMvc.perform(get("/api/plataformas/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value("Plataforma 1"));

    verify(plataformaService, times(1)).buscarPorId("1");
  }

  @Test
  public void testDeletarPlataforma() throws Exception {
    // Quando
    doNothing().when(plataformaService).deletarPlataforma("1");

    // Então
    mockMvc.perform(delete("/api/plataformas/1"))
        .andExpect(status().isNoContent());

    verify(plataformaService, times(1)).deletarPlataforma("1");
  }

  @Test
  public void testAdicionarCursoParaAluno() throws Exception {
    // Quando
    mockMvc.perform(post("/api/plataformas/1/alunos/Aluno 1/cursos")
        .param("nomeCurso", "Curso 1"))
        .andExpect(status().isOk());

    // Verifica se o método foi chamado
    verify(plataformaService, times(1)).adicionarCursoParaAluno(eq("1"), eq("Aluno 1"), eq("Curso 1"));
  }

  @Test
  public void testAlunoConcluiCurso() throws Exception {
    // Quando
    mockMvc.perform(post("/api/plataformas/1/alunos/Aluno 1/cursos/Curso 1/concluir")
        .param("notaFinal", "9.5"))
        .andExpect(status().isOk());

    // Verifica se o método foi chamado
    verify(plataformaService, times(1)).alunoConcluiCurso(eq("1"), eq("Aluno 1"), eq("Curso 1"), eq(9.5));
  }
}
