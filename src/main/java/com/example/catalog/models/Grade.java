package com.example.catalog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@Table(name = "Grade")
@AllArgsConstructor
@NoArgsConstructor
public class Grade {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer grade;

    @Column
    private String date;

    public Grade(Integer grade) {
        this.grade = grade;
    }
}
