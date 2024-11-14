package com.example.Ac2_project.entity.curso;

import java.io.Serializable;
import java.util.List;

import com.example.Ac2_project.entity.aluno.Aluno;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Curso implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID) 
  private String id;
  private String nome;
  private boolean concluido;
  private double notaFinal;

  @ManyToMany(mappedBy = "cursos")
  private List<Aluno> alunos;

  public Curso(String nome) {
      this.nome = nome;
      this.concluido = false;
  }

  public String getNome() {
      return nome;
  }

  public boolean isConcluido() {
      return concluido;
  }

  public double getNotaFinal() {
      return notaFinal;
  }

  public boolean concluirCurso(double notaFinal) {
      this.notaFinal = notaFinal;
      if (notaFinal >= 7.0) {
          this.concluido = true;
      }
      return concluido;
  }
}
