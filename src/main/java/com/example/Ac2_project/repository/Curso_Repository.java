package com.example.Ac2_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ac2_project.entity.curso.Curso;


@Repository
public interface Curso_Repository extends JpaRepository<Curso, String> {

}
