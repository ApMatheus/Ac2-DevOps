package com.example.Ac2_project.service;

import com.example.Ac2_project.entity.aluno.Aluno;
import com.example.Ac2_project.entity.curso.Curso;
import com.example.Ac2_project.entity.plataforma.PlataformaCursos;
import com.example.Ac2_project.repository.Plataforma_Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlataformaService {

    @Autowired
    private Plataforma_Repository plataformaRepository;

    // Salva uma nova plataforma
    public PlataformaCursos salvarPlataforma(PlataformaCursos plataforma) {
        return plataformaRepository.save(plataforma);
    }

    // Atualiza uma plataforma existente
    public PlataformaCursos atualizarPlataforma(String id, PlataformaCursos plataformaAtualizada) {
        Optional<PlataformaCursos> plataformaExistente = plataformaRepository.findById(id);
        if (plataformaExistente.isPresent()) {
            PlataformaCursos plataforma = plataformaExistente.get();
            plataforma.setNome(plataformaAtualizada.getNome());
            return plataformaRepository.save(plataforma);
        }
        return null;
    }

    // Busca uma plataforma por ID
    public PlataformaCursos buscarPorId(String id) {
        return plataformaRepository.findById(id).orElse(null);
    }

    // Deleta uma plataforma por ID
    public void deletarPlataforma(String id) {
        plataformaRepository.deleteById(id);
    }

    // Lista todas as plataformas
    public List<PlataformaCursos> listarTodas() {
        return plataformaRepository.findAll();
    }

    // Adiciona um aluno a uma plataforma
    public PlataformaCursos adicionarAluno(String plataformaId, Aluno aluno) {
        Optional<PlataformaCursos> plataformaOpt = plataformaRepository.findById(plataformaId);
        if (plataformaOpt.isPresent()) {
            PlataformaCursos plataforma = plataformaOpt.get();
            plataforma.adicionarAluno(aluno);
            return plataformaRepository.save(plataforma);
        }
        return null;
    }

    // Adiciona um curso para um aluno específico
    public void adicionarCursoParaAluno(String plataformaId, String nomeAluno, String nomeCurso) {
        Optional<PlataformaCursos> plataformaOpt = plataformaRepository.findById(plataformaId);
        if (plataformaOpt.isPresent()) {
            PlataformaCursos plataforma = plataformaOpt.get();
            plataforma.adicionarCursoParaAluno(nomeAluno, nomeCurso);
            plataformaRepository.save(plataforma);
        }
    }

    // Conclui um curso de um aluno específico
    public void alunoConcluiCurso(String plataformaId, String nomeAluno, String nomeCurso, double notaFinal) {
        Optional<PlataformaCursos> plataformaOpt = plataformaRepository.findById(plataformaId);
        if (plataformaOpt.isPresent()) {
            PlataformaCursos plataforma = plataformaOpt.get();
            plataforma.alunoConcluiCurso(nomeAluno, nomeCurso, notaFinal);
            plataformaRepository.save(plataforma);
        }
    }
}
