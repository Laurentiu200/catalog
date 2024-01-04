package com.example.catalog.service;

import com.example.catalog.models.Student;
import com.example.catalog.models.Profesor;
import com.example.catalog.models.Role;
import com.example.catalog.models.User;
import com.example.catalog.repository.StudentRepository;
import com.example.catalog.repository.ProfesorRepository;
import com.example.catalog.responses.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    StudentRepository elevRepository;

    @Autowired
    ProfesorRepository profesorRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public LoginResponse login(User user) {
        if (user.getRole().equals(Role.STUDENT)) {
            Student student = elevRepository.findByEmail(user.getEmail());

            if (student != null) {
                if (passwordEncoder.matches(user.getPassword(), student.getPassword())) {
                    return new LoginResponse("Success", "Authentication success!");
                }
                return new LoginResponse("Invalid", "Invalid password!");
            }
            return new LoginResponse("Invalid", "Invalid email!");
        }
        else
        {
            Profesor profesor = profesorRepository.findByEmail(user.getEmail());

            if (profesor != null) {
                if (passwordEncoder.matches(user.getPassword(), profesor.getPassword())) {
                    return new LoginResponse("Success", "Authentication success!");
                }
                return new LoginResponse("Invalid", "Invalid password!");
            }
            return new LoginResponse("Invalid", "Invalid email!");
        }
    }



    public LoginResponse saveStudent(Student student) {
        Student student1 = elevRepository.findByEmail(student.getEmail());
        if (student1 != null) {
            return new LoginResponse("Invalid", "Email Already exists!");
        } else {
            if (!student.getEmail().contains("@") && !student.getEmail().contains(".com")) {
                return new LoginResponse("Invalid", "Email must be a valid email!");
            }
            if (student.getPassword().length() < 10) {
                return new LoginResponse("Invalid", "Password must contains at least 10 characters!");
            }
        }
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        elevRepository.save(student);

        return new LoginResponse("Success", "This student was saved!");
    }

        public LoginResponse saveProfessor(Profesor profesor) {
            Profesor profesor1 = profesorRepository.findByEmail(profesor.getEmail());
            if (profesor1 != null) {
                return new LoginResponse("Invalid", "Email Already exists!");
            } else {
                if (!profesor.getEmail().contains("@") && !profesor.getEmail().contains(".com")) {
                    return new LoginResponse("Invalid", "Email must be a valid email!");
                }
                if (profesor.getPassword().length() < 10) {
                    return new LoginResponse("Invalid", "Password must contains at least 10 characters!");
                }
            }
            profesor.setPassword(passwordEncoder.encode(profesor.getPassword()));
            profesorRepository.save(profesor);

            return new LoginResponse("Success", "This professor was saved!");

        }
}
