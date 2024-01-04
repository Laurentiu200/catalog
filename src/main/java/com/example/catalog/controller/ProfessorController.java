package com.example.catalog.controller;

import com.example.catalog.models.Grade;
import com.example.catalog.models.Materie;
import com.example.catalog.models.Student;
import com.example.catalog.repository.StudentRepository;
import com.example.catalog.service.StudentServiceInterface;
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
