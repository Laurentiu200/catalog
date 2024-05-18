package com.example.catalog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@Table(name = "ANSWER")
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column( name = "name")
    String answer;
}
