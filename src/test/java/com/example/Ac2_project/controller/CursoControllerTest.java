package com.example.Ac2_project.controller;

import com.example.Ac2_project.controller.CursoController;
import com.example.Ac2_project.entity.curso.Curso;
import com.example.Ac2_project.service.CursoService;
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
public class CursoControllerTest {

  @Mock
  private CursoService cursoService;

  @InjectMocks
  private CursoController cursoController;

  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(cursoController).build();
  }


  @Test
  public void testDeletarCurso() throws Exception {
    // Quando
    doNothing().when(cursoService).deletarCurso("1");

    // Então
    mockMvc.perform(delete("/api/cursos/1"))
        .andExpect(status().isNoContent());

    verify(cursoService, times(1)).deletarCurso("1");
  }

}
