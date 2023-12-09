package com.example.catalog.serviceImpl;

import com.example.catalog.models.Elev;
import com.example.catalog.models.User;
import com.example.catalog.responses.LoginResponse;

import java.util.Optional;

public interface ManageStudentService {
    LoginResponse saveStudent(Elev elev);
    LoginResponse login(User user);
    public Optional<Elev> findElev(Integer id);
}
