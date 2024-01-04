package com.example.catalog.service;

import com.example.catalog.models.Student;

import java.util.List;

public interface StudentServiceInterface {
    Student addStudent(Student student);
    List<Student> getStudents();
    Student updateStudent(Student student, Integer id);
    Student getStudentById(Integer id);
    void deleteStudent(Integer id);
}
