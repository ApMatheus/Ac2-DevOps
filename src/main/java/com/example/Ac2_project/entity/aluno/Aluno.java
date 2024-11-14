package com.example.Ac2_project.entity.aluno;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.Ac2_project.entity.curso.Curso;
import com.example.Ac2_project.entity.plataforma.PlataformaCursos;

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
public class Aluno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String senha;
    private boolean isPremium;
    private int moedas;

    @ManyToOne
    private PlataformaCursos plataforma;

    @Embedded
    private Email email;

    @ManyToMany
    @JoinTable(name = "aluno_curso", joinColumns = @JoinColumn(name = "aluno_id"), inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Curso> cursos = new ArrayList<>();

    public Aluno(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
        this.moedas = 0;
    }

    public String getNome() {
        return nome;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public int getMoedas() {
        return moedas;
    }

    public void adicionarCurso(Curso curso) {
        this.cursos.add(curso);
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public boolean autenticar(String senha) {
        return this.senha.equals(senha);
    }

    public void concluirCurso(String nomeCurso, double notaFinal) {
        Curso curso = buscarCurso(nomeCurso);
        if (curso != null) {
            curso.concluirCurso(notaFinal);
            if (curso.isConcluido()) {
                verificarPremium();
            }
        } else {
            System.out.println("Curso não encontrado.");
        }
    }

    private Curso buscarCurso(String nomeCurso) {
        return this.cursos.stream()
                .filter(curso -> curso.getNome().equalsIgnoreCase(nomeCurso))
                .findFirst()
                .orElse(null);
    }

    private void verificarPremium() {
        long cursosConcluidos = cursos.stream().filter(Curso::isConcluido).count();
        if (cursosConcluidos >= 12 && !this.isPremium) {
            this.isPremium = true;
            this.moedas += 3;
            System.out.println("Aluno " + this.nome + " agora é Premium e recebeu 3 moedas!");
        }
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }
}
