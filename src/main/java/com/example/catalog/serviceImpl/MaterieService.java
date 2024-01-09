package com.example.catalog.serviceImpl;

import com.example.catalog.models.*;
import com.example.catalog.repository.MaterieRepository;
import com.example.catalog.repository.ProfesorRepository;
import com.example.catalog.repository.StudentRepository;
import com.example.catalog.responses.LoginResponse;
import com.example.catalog.service.StudentServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    StudentServiceInterface studentServiceInterface;

    @Autowired
    ProfesorRepository profesorRepository;

    public LoginResponse addStudentsToAClass(String student, String className, String teacherEmail)
    {
        List<Grade> grades = new ArrayList<>();
        Student student1 = studentRepository.findByEmail(student);
        Profesor profesor = profesorRepository.findByEmail(teacherEmail);
        student1.getCourses().add(new Materie(className,profesor, grades));
        studentRepository.save(student1);
        return new LoginResponse("Success", "Course Added");
    }

    public Student addGrade(Integer studentEmail, MaterieNota materieNota)
    {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = today.format(formatter);
        Student student = studentServiceInterface.getStudentById(studentEmail);
        student.getCourses().forEach(course -> {
            if(course.getId().equals(materieNota.getMaterie()))
            {
                Grade grade = new Grade();
                grade.setGrade(materieNota.getNota());
                grade.setDate(formattedDate);
                course.getGrades().add(grade);
            }
        });

        studentRepository.save(student);

        return student;
    }
}
