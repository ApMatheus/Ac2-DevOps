package com.example.Ac2_project.dto;

import com.example.Ac2_project.entity.aluno.Aluno;
import com.example.Ac2_project.entity.curso.Curso;
import com.example.Ac2_project.entity.plataforma.PlataformaCursos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlunoDTO {
  private String id;
  private String nome;
  private boolean isPremium;
  private int moedas;
  private PlataformaCursos plataforma;
  private List<Curso> cursos;

  public static AlunoDTO fromEntity(Aluno aluno) {
    return AlunoDTO.builder()
        .id(aluno.getId())
        .nome(aluno.getNome())
        .isPremium(aluno.isPremium())
        .moedas(aluno.getMoedas())
        .plataforma(aluno.getPlataforma())
        .cursos(aluno.getCursos())
        .build();
  }

  public static Aluno toEntity(AlunoDTO alunoDTO) {
    return Aluno.builder()
        .id(alunoDTO.getId())
        .nome(alunoDTO.getNome())
        .isPremium(alunoDTO.isPremium())
        .moedas(alunoDTO.getMoedas())
        .plataforma(alunoDTO.getPlataforma())
        .cursos(alunoDTO.getCursos())
        .build();
  }
}
