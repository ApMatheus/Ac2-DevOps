package com.example.Ac2_project.entity.plataforma;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.example.Ac2_project.entity.aluno.Aluno;
import com.example.Ac2_project.entity.curso.Curso;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
public class PlataformaCursos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) 
    private String id;

    private String nome;

    @OneToMany(mappedBy = "plataforma")
    private List<Aluno> alunos = new ArrayList<>();

    public void adicionarAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public boolean autenticarAluno(String nome, String senha) {
        Aluno aluno = buscarAluno(nome);
        if (aluno != null && aluno.autenticar(senha)) {
            System.out.println("Login bem-sucedido!");
            return true;
        }
        System.out.println("Falha no login. Verifique seu nome ou senha.");
        return false;
    }

    public void adicionarCursoParaAluno(String nomeAluno, String nomeCurso) {
        Aluno aluno = buscarAluno(nomeAluno);
        if (aluno != null) {
            Curso curso = new Curso(nomeCurso);
            aluno.adicionarCurso(curso);
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    public void alunoConcluiCurso(String nomeAluno, String nomeCurso, double notaFinal) {
        Aluno aluno = buscarAluno(nomeAluno);
        if (aluno != null) {
            aluno.concluirCurso(nomeCurso, notaFinal);
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    Aluno buscarAluno(String nome) {
        return this.alunos.stream()
                .filter(aluno -> aluno.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }
}
