package com.example.catalog.controller;

import com.example.catalog.models.Grade;
import com.example.catalog.models.Materie;
import com.example.catalog.models.MaterieNota;
import com.example.catalog.models.Student;
import com.example.catalog.repository.StudentRepository;
import com.example.catalog.service.StudentServiceInterface;
import com.example.catalog.serviceImpl.MaterieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000") //allowing client application to consume the backed
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class ProfessorController {
    private final StudentServiceInterface studentService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MaterieService materieService;
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.FOUND);
    }
    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }
    @PutMapping("/update/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable Integer id){
        return studentService.updateStudent(student, id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
    }
    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable Integer id){
        return studentService.getStudentById(id);
    }

    @GetMapping(path = "/getCourses/{id}")
    public List<Materie> getMaterii(@PathVariable Integer id)
    {
        return studentService.getStudentById(id).getCourses();
    }


    @GetMapping(path = "/getCourseName/{id}/{courseId}")
    @ResponseBody
    public String getMaterieName(@PathVariable Integer id, @PathVariable Integer courseId)
    {
        Student student = studentService.getStudentById(id);
        List<Materie> materies = student.getCourses();
        for (int i = 0; i < materies.size(); i++)
            if(materies.get(i).getId().equals(courseId))
            {
                return materies.get(i).getName();
            }

        return null;
    }

    @GetMapping(path = "/getCourse/{id}/{courseId}")
    @ResponseBody
    public List<Grade> getMaterie(@PathVariable Integer id, @PathVariable Integer courseId)
    {
        Student student = studentService.getStudentById(id);
        List<Materie> materies = student.getCourses();
        for (int i = 0; i < materies.size(); i++)
            if(materies.get(i).getId().equals(courseId))
            {
                return materies.get(i).getGrades();
            }

        return null;
    }
    @PatchMapping (path = "/addGrade/{id}/{courseId}/{grade}")
    @ResponseBody
    public Student addGrade(@PathVariable Integer id, @PathVariable Integer courseId, @PathVariable Integer grade)
    {
        MaterieNota materieNota = new MaterieNota(courseId, grade);
        return materieService.addGrade(id, materieNota);
    }
    @PatchMapping(path = "/deleteGrade/{studentId}/{courseId}/{gradeId}")
    public void deleteGrade(@PathVariable Integer studentId, @PathVariable Integer courseId, @PathVariable Integer gradeId)
    {
        Student student = studentService.getStudentById(studentId);
        List<Materie> materii = student.getCourses();

        for(int i = 0; i < materii.size(); i++)
        {
            if(materii.get(i).getId().equals(courseId)) {
                List<Grade> grades = materii.get(i).getGrades();
                for (int j = 0; j < grades.size(); j++) {
                    if (grades.get(j).getId().equals(gradeId)) {
                        materii.get(i).getGrades().remove(grades.get(j));
                    }
                }
            }
        }

        studentRepository.save(student);
    }



}
