package com.example.catalog.repository;

import com.example.catalog.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Student, Integer> {

    Student findByEmail(String email);
}
