package com.example.catalog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "PROFESOR")
@AllArgsConstructor
@NoArgsConstructor
public class Profesor {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "Materie")
    private Integer materie;

    @Column(name = "Nr.Elevi")
    private String nr_elevi;


}
