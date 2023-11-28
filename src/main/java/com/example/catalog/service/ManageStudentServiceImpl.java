package com.example.catalog.service;

import com.example.catalog.models.Elev;
import com.example.catalog.repository.ElevRepository;
import com.example.catalog.serviceImpl.ManageStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManageStudentServiceImpl implements ManageStudentService {

    @Autowired
    ElevRepository elevRepository;

    @Override
    public void saveStudent(Elev elev)
    {
        elevRepository.save(elev);

    }

    public Optional<Elev> findElev(Integer id)
    {
        return elevRepository.findById(id);
    }
}
