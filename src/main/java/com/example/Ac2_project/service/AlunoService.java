package com.example.Ac2_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Ac2_project.entity.aluno.Aluno;
import com.example.Ac2_project.entity.curso.Curso;
import com.example.Ac2_project.repository.Aluno_Repository;

@Service
public class AlunoService {

    @Autowired
    private Aluno_Repository alunoRepository;

    // Salva um novo aluno
    public Aluno salvarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    // Atualiza um aluno existente
    public Aluno atualizarAluno(String id, Aluno alunoAtualizado) {
        Optional<Aluno> alunoExistente = alunoRepository.findById(id);
        if (alunoExistente.isPresent()) {
            Aluno aluno = alunoExistente.get();
            aluno.setNome(alunoAtualizado.getNome());
            aluno.setSenha(alunoAtualizado.getSenha());
            aluno.setMoedas(alunoAtualizado.getMoedas());
            aluno.setCursos(alunoAtualizado.getCursos());
            aluno.setPremium(alunoAtualizado.isPremium());
            return alunoRepository.save(aluno);
        }
        return null;
    }

    // Busca um aluno por ID
    public Aluno buscarPorId(String id) {
        return alunoRepository.findById(id).orElse(null);
    }

    // Deleta um aluno por ID
    public void deletarAluno(String id) {
        alunoRepository.deleteById(id);
    }

    // Busca todos os alunos
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    // Adiciona um curso ao aluno
    public Aluno adicionarCurso(String alunoId, Curso curso) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(alunoId);
        if (alunoOpt.isPresent()) {
            Aluno aluno = alunoOpt.get();
            aluno.adicionarCurso(curso);
            return alunoRepository.save(aluno);
        }
        return null;
    }

    // Conclui um curso de um aluno
    public Aluno concluirCurso(String alunoId, String nomeCurso, double notaFinal) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(alunoId);
        if (alunoOpt.isPresent()) {
            Aluno aluno = alunoOpt.get();
            aluno.concluirCurso(nomeCurso, notaFinal);
            return alunoRepository.save(aluno);
        }
        return null;
    }
}
