package com.example.catalog.controller;


import com.example.catalog.models.*;
import com.example.catalog.repository.MaterieRepository;
import com.example.catalog.repository.ProfesorRepository;
import com.example.catalog.repository.StudentRepository;
import com.example.catalog.responses.LoginResponse;
import com.example.catalog.serviceImpl.MaterieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/materie")
public class MaterieController {

    @Autowired
    MaterieRepository materieRepository;

    @Autowired
    MaterieService materieService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    @PostMapping(path = "/addMaterie")
    public LoginResponse addStudent(@RequestBody Classes materie)
    {
        Materie materie1= new Materie();
        materie1.setName(materie.getName());
        materie1.setProfesor(profesorRepository.findByEmail(materie.getTeacherEmail()));
        materieRepository.save(materie1);
        return new LoginResponse("success", "materie added");
    }

    @PatchMapping(path = "/addStudent")
    @ResponseBody
    public Student Login(@RequestBody MaterieStudent materie)
    {
        return materieService.addStudentsToAClass(materie.getStudentEmail(), materie.getNameMaterie(), materie.getTeacherEmail());
    }

    @PatchMapping(path = "/addGrade")
    @ResponseBody
    public Student addGrade(@RequestParam String email, @RequestBody MaterieNota materieNota)
    {
        return materieService.addGrade(email, materieNota);
    }

    @GetMapping(path = "/getCourses")
    @ResponseBody
    public List<Materie> getMaterii(@RequestParam String email)
    {
        return studentRepository.findByEmail(email).getCourses();
    }

}