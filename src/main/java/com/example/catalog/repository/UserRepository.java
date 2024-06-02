package com.example.catalog.repository;

import com.example.catalog.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Student, Integer> {

    Student findByEmail(String email);

    @Modifying
    @Query("update Student u set u.result = ?1, u.quiuri = ?2 where u.id = ?3")
    void updateUser(Integer result, List<Integer> quizId, Integer userId);
}
