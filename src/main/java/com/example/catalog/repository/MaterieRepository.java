package com.example.catalog.repository;

import com.example.catalog.models.Materie;
import com.example.catalog.models.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterieRepository extends JpaRepository<Materie, Integer> {
    Materie findByName(String name);


}
