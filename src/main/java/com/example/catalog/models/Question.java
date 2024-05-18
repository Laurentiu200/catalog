package com.example.catalog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

@Entity
@Data
@Builder
@Table(name = "QUESTION")
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(name = "questionName")
    String question;

    @OneToOne(targetEntity = Answer.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "answer1Id", referencedColumnName = "id")
    Answer var1;

    @OneToOne(targetEntity = Answer.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "answer2Id", referencedColumnName = "id")
    Answer var2;

    @OneToOne(targetEntity = Answer.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "answer3Id", referencedColumnName = "id")
    Answer var3;

    @OneToOne(targetEntity = Answer.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "answer4Id", referencedColumnName = "id")
    Answer var4;

    @Column(name = "correctVar")
    Integer answer;

}
