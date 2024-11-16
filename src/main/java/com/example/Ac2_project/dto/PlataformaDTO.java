package com.example.Ac2_project.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.Ac2_project.entity.aluno.Aluno;
import com.example.Ac2_project.entity.plataforma.PlataformaCursos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlataformaDTO {
  private String id;
  private String nome;
  private List<String> alunos; // Apenas os nomes dos alunos para simplificar.

  public static PlataformaDTO fromEntity(PlataformaCursos plataforma) {
    return PlataformaDTO.builder()
        .id(plataforma.getId())
        .nome(plataforma.getNome())
        .alunos(plataforma.getAlunos().stream()
            .map(Aluno::getNome) // Inclui apenas os nomes dos alunos.
            .collect(Collectors.toList()))
        .build();
  }

  public static PlataformaCursos toEntity(PlataformaDTO plataformaDTO) {
    return PlataformaCursos.builder()
        .id(plataformaDTO.getId())
        .nome(plataformaDTO.getNome())
        .build(); // A lista de alunos não é gerada automaticamente, pois depende da lógica do
                  // sistema.
  }

}
