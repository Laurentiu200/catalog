package com.example.catalog.service;

import com.example.catalog.models.Student;
import com.example.catalog.models.User;
import com.example.catalog.repository.StudentRepository;
import com.example.catalog.responses.LoginResponse;
import com.example.catalog.serviceImpl.ManageStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
public class ManageStudentServiceImpl implements ManageStudentService {

    @Autowired
    StudentRepository elevRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse saveStudent(Student student)
    {
        Student student1 = elevRepository.findByEmail(student.getEmail());
        if(student1 != null)
        {
            return new LoginResponse("Invalid", "Email Already exists!");
        }
        else
        {
            if(!student.getEmail().contains("@") && !student.getEmail().contains(".com"))
            {
                return new LoginResponse("Invalid", "Email must be a valid email!");
            }
            if(student.getPassword().length() < 10)
            {
                return new LoginResponse("Invalid", "Password must contains at least 10 characters!");
            }
        }
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        elevRepository.save(student);

        return new LoginResponse("Success", "This student was saved!");

    }

    public LoginResponse login(User user)
    {
        Student student = elevRepository.findByEmail(user.getEmail());

        if(student != null)
        {
            if(passwordEncoder.matches(user.getPassword(), student.getPassword()))
            {
                return new LoginResponse("Success", "Authentication success!");
            }
                return new LoginResponse("Invalid", "Invalid password!");
        }
        return new LoginResponse("Invalid", "Invalid email!");
    }

    public Optional<Student> findElev(Integer id)
    {
        return elevRepository.findById(id);
    }
}
