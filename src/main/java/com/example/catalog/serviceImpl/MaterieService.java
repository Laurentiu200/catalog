package com.example.catalog.serviceImpl;

import com.example.catalog.models.*;
import com.example.catalog.repository.MaterieRepository;
import com.example.catalog.repository.ProfesorRepository;
import com.example.catalog.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MaterieService {

    @Autowired
    MaterieRepository materieRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    public Student addStudentsToAClass(String student, String className, String teacherEmail)
    {
        List<Grade> grades = new ArrayList<>();
        Student student1 = studentRepository.findByEmail(student);
        Profesor profesor = profesorRepository.findByEmail(teacherEmail);
        student1.getCourses().add(new Materie(2,className,profesor, grades));
        studentRepository.save(student1);
        return student1;
    }

    public Student addGrade(String studentEmail, MaterieNota materieNota)
    {
        Student student = studentRepository.findByEmail(studentEmail);
        student.getCourses().forEach(course -> {
            if(course.getName().equals(materieNota.getMaterie()))
            {
                course.getGrades().add(new Grade(materieNota.getNota()));
            }
        });

        studentRepository.save(student);

        return student;
    }
}
