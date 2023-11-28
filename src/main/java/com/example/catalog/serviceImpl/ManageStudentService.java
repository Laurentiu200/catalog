package com.example.catalog.serviceImpl;

import com.example.catalog.models.Elev;

import java.util.Optional;

public interface ManageStudentService {
    void saveStudent(Elev elev);
    public Optional<Elev> findElev(Integer id);
}
