package com.example.catalog.repository;

import com.example.catalog.models.Elev;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ElevRepository extends JpaRepository<Elev, Integer> {

    Elev findByEmail(String email);
}
