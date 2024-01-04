package com.example.catalog.serviceImpl;

import com.example.catalog.models.Student;
import com.example.catalog.models.User;
import com.example.catalog.responses.LoginResponse;

import java.util.Optional;

public interface ManageStudentService {
    LoginResponse saveStudent(Student student);
    LoginResponse login(User user);
    public Optional<Student> findElev(Integer id);
}
