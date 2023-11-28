package com.example.catalog.controller;

import com.example.catalog.models.Elev;
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
    public void addElev(@RequestBody Elev elev)
    {
        manageStudentService.saveStudent(elev);
    }

    @GetMapping(path = "/find")
    public Optional<Elev> findElev(@RequestParam Integer id)
    {
        return manageStudentService.findElev(id);
    }
}
