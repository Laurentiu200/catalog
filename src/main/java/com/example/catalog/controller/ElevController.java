package com.example.catalog.controller;

import com.example.catalog.models.Elev;
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
    public LoginResponse addElev(@RequestBody Elev elev)
    {
        LoginResponse response = manageStudentService.saveStudent(elev);
        return response;
    }
    @PostMapping(path = "/login")
    public LoginResponse addElev(@RequestBody User user)
    {
        LoginResponse response = manageStudentService.login(user);
        return response;
    }


    @GetMapping(path = "/find")
    public Optional<Elev> findElev(@RequestParam Integer id)
    {
        return manageStudentService.findElev(id);
    }
}
