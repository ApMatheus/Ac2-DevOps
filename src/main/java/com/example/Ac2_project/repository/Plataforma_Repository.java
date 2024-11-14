package com.example.Ac2_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Ac2_project.entity.plataforma.PlataformaCursos;


@Repository
public interface Plataforma_Repository extends JpaRepository<PlataformaCursos, String> {

}
