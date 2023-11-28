package com.example.catalog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@Table(name = "ELEV")
@AllArgsConstructor
@NoArgsConstructor
public class Elev {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "nota")
    private Integer nota;

    @Column(name = "ceva")
    private Integer ceva;
}
