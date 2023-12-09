package com.example.catalog.controller;


import com.example.catalog.models.Profesor;
import com.example.catalog.serviceImpl.ManageProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    ManageProfessorService manageProfesorService;

    @PostMapping(path = "/addUser")
    public void addProfesor(@RequestBody Profesor profesor) {manageProfesorService.saveProfessor(profesor);}

    @GetMapping(path = "/find")
    public Optional<Profesor> findProfesor(@RequestParam Integer id) {return manageProfesorService.findProfesor(id);}
}
