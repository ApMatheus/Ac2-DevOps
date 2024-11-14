package com.example.Ac2_project.dto;

import java.util.List;

import com.example.Ac2_project.entity.curso.Curso;

public class AlunoDTO {
  private String nome;
  private String senha;
  private List<Curso> cursos;
  private boolean isPremium;
  private int moedas;

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public List<Curso> getCursos() {
    return cursos;
  }

  public void setCursos(List<Curso> cursos) {
    this.cursos = cursos;
  }

  public boolean isPremium() {
    return isPremium;
  }

  public void setPremium(boolean isPremium) {
    this.isPremium = isPremium;
  }

  public int getMoedas() {
    return moedas;
  }

  public void setMoedas(int moedas) {
    this.moedas = moedas;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
