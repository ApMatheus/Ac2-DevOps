package com.example.Ac2_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ac2_project.entity.aluno.Aluno;

@Repository
public interface Aluno_Repository extends JpaRepository<Aluno, String> {

}
