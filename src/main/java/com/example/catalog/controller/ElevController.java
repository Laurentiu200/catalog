package com.example.catalog.controller;

import com.example.catalog.models.Student;
import com.example.catalog.models.User;
import com.example.catalog.responses.LoginResponse;
import com.example.catalog.serviceImpl.ManageStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/elev")
public class ElevController {

    @Autowired
    ManageStudentService manageStudentService;

    @PostMapping(path = "/addUser")
    public LoginResponse addElev(@RequestBody Student student)
    {
        LoginResponse response = manageStudentService.saveStudent(student);
        return response;
    }
    @PostMapping(path = "/login")
    public LoginResponse addElev(@RequestBody User user)
    {
        LoginResponse response = manageStudentService.login(user);
        return response;
    }


    @GetMapping(path = "/find")
    public Optional<Student> findElev(@RequestParam Integer id)
    {
        return manageStudentService.findElev(id);
    }
}
