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
@Table(name = "ELEV")
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name = "result")
    private Integer result;

    @ElementCollection
    @Column(name = "quizRezolvat")
    private List<Integer> quiuri;

    public void addQuiz(Integer id)
    {
        this.quiuri.add(id);
    }
}
