package com.example.catalog;

import com.example.catalog.models.Elev;
import com.example.catalog.repository.ElevRepository;
import com.example.catalog.models.Profesor;
import com.example.catalog.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogApplication.class, args);

    }

}
