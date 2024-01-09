package com.example.catalog.controller;


import com.example.catalog.models.*;
import com.example.catalog.repository.MaterieRepository;
import com.example.catalog.repository.ProfesorRepository;
import com.example.catalog.repository.StudentRepository;
import com.example.catalog.responses.LoginResponse;
import com.example.catalog.service.StudentServiceInterface;
import com.example.catalog.serviceImpl.MaterieService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
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

    @Autowired
    StudentServiceInterface studentService;

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
    public LoginResponse Login(@RequestBody MaterieStudent materie)
    {
        return materieService.addStudentsToAClass(materie.getStudentEmail(), materie.getNameMaterie(), materie.getTeacherEmail());
    }

    @PatchMapping(path = "/addGrade/{id}/{courseId}/{grade}")
    @ResponseBody
    public Student addGrade(@PathVariable Integer id, @PathVariable Integer courseId, @PathVariable Integer grade)
    {
        MaterieNota materieNota = new MaterieNota(courseId, grade);
        return materieService.addGrade(id, materieNota);
    }

    @GetMapping(path = "/getCourses")
    @ResponseBody
    public List<Materie> getMaterii(@RequestParam String email)
    {
        return studentRepository.findByEmail(email).getCourses();
    }



    @PatchMapping (path = "/editGrade")
    @ResponseBody
    public void editGrade(@RequestParam Integer id, @RequestParam Integer courseId, @RequestParam Integer gradeId, @RequestParam Integer grade) {
        Student student = studentService.getStudentById(id);
        List<Materie> materies = student.getCourses();
        for (int i = 0; i < materies.size(); i++)
            if(materies.get(i).getId().equals(courseId))
            {
                List<Grade> grades = materies.get(i).getGrades();
                for (int j = 0; j < grades.size(); j++)
                {
                    if(grades.get(j).getId().equals(gradeId))
                        grades.get(j).setGrade(grade);
                }
            }
        studentRepository.save(student);
    }

}
