package com.example.catalog.repository;

import com.example.catalog.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository  extends JpaRepository<Quiz, Integer> {
}
