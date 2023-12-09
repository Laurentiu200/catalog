package com.example.catalog.service;

import com.example.catalog.models.Elev;
import com.example.catalog.models.User;
import com.example.catalog.repository.ElevRepository;
import com.example.catalog.responses.LoginResponse;
import com.example.catalog.serviceImpl.ManageStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.DelegatingServerHttpResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
public class ManageStudentServiceImpl implements ManageStudentService {

    @Autowired
    ElevRepository elevRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse saveStudent(Elev elev)
    {
        Elev elev1 = elevRepository.findByEmail(elev.getEmail());
        if(elev1 != null)
        {
            return new LoginResponse("Invalid", "Email Already exists!");
        }
        else
        {
            if(!elev.getEmail().contains("@") && !elev.getEmail().contains(".com"))
            {
                return new LoginResponse("Invalid", "Email must be a valid email!");
            }
            if(elev.getPassword().length() < 10)
            {
                return new LoginResponse("Invalid", "Password must contains at least 10 characters!");
            }
        }
        elev.setPassword(passwordEncoder.encode(elev.getPassword()));
        elevRepository.save(elev);

        return new LoginResponse("Success", "This student was saved!");

    }

    public LoginResponse login(User user)
    {
        Elev elev = elevRepository.findByEmail(user.getEmail());

        if(elev != null)
        {
            if(passwordEncoder.matches(user.getPassword(), elev.getPassword()))
            {
                return new LoginResponse("Success", "Authentication success!");
            }
                return new LoginResponse("Invalid", "Invalid password!");
        }
        return new LoginResponse("Invalid", "Invalid email!");
    }

    public Optional<Elev> findElev(Integer id)
    {
        return elevRepository.findById(id);
    }
}
