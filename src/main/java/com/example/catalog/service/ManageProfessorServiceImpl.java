package com.example.catalog.service;


import com.example.catalog.models.Profesor;

import com.example.catalog.repository.ProfesorRepository;

import com.example.catalog.serviceImpl.ManageProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManageProfessorServiceImpl implements ManageProfessorService {

    @Autowired
    ProfesorRepository profesorRepository;

    @Override
    public void saveProfessor(Profesor profesor)
    {
        profesorRepository.save(profesor);

    }

    public Optional<Profesor> findProfesor(Integer id)
    {
        return profesorRepository.findById(id);
    }
}
