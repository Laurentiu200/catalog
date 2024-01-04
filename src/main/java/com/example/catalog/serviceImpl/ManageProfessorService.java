package com.example.catalog.serviceImpl;

import com.example.catalog.models.Profesor;

import java.util.Optional;

public interface ManageProfessorService {
    void saveProfessor(Profesor profesor);
    Optional<Profesor> findProfesor(Integer id);
}
