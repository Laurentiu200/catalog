package com.example.catalog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@Table(name = "Quiz")
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(targetEntity = Question.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "questions", referencedColumnName = "id")
    private List<Question> questions;
}
