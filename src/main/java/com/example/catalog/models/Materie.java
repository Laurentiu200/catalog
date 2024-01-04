package com.example.catalog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@Table(name = "Materie")
@AllArgsConstructor
@NoArgsConstructor
public class Materie {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToOne
    private Profesor profesor;

    @OneToMany(targetEntity = Grade.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "materie_fk", referencedColumnName = "id")
    private List<Grade> grades;

    public Materie(String name, Profesor profesor, List<Grade> grades) {
        this.name = name;
        this.profesor = profesor;
        this.grades = grades;
    }
}
