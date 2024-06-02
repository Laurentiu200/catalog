package com.example.catalog.controller;

import com.example.catalog.models.Student;
import com.example.catalog.models.Profesor;
import com.example.catalog.models.User;
import com.example.catalog.responses.LoginResponse;
import com.example.catalog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    LoginService loginService;
    @PostMapping(path = "/login")
    public LoginResponse Login(@RequestBody User user)
    {
        LoginResponse response = loginService.login(user);
        return response;
    }


    @PostMapping(path = "/addStudent")
    public LoginResponse Login(@RequestBody Student student)
    {
        LoginResponse response = loginService.saveStudent(student);
        return response;
    }


}
