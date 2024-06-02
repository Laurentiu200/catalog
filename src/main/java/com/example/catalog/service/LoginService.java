package com.example.catalog.service;

import com.example.catalog.models.Student;
import com.example.catalog.models.Profesor;
import com.example.catalog.models.User;
import com.example.catalog.repository.UserRepository;
import com.example.catalog.repository.ProfesorRepository;
import com.example.catalog.responses.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserRepository elevRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public LoginResponse login(User user) {
            Student student = elevRepository.findByEmail(user.getEmail());

            if (student != null) {
                if (passwordEncoder.matches(user.getPassword(), student.getPassword())) {
                    return new LoginResponse("Success", "Authentication success!", student.getId());
                }
                return new LoginResponse("Invalid", "Invalid password!", student.getId());
            }
            return new LoginResponse("Invalid", "Invalid email!", student.getId());
    }

    public Integer getUserIdByEmail(String email)
    {
        return elevRepository.findByEmail(email).getId();
    }



    public LoginResponse saveStudent(Student student) {
        Student student1 = elevRepository.findByEmail(student.getEmail());
        if (student1 != null) {
            return new LoginResponse("Invalid", "Email Already exists!", student.getId());
        } else {
            if (!student.getEmail().contains("@") && !student.getEmail().contains(".com")) {
                return new LoginResponse("Invalid", "Email must be a valid email!", student.getId());
            }
            if (student.getPassword().length() < 10) {
                return new LoginResponse("Invalid", "Password must contains at least 10 characters!", student.getId());
            }
        }
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        elevRepository.save(student);

        return new LoginResponse("Success", "This student was saved!", student.getId());
    }

}
