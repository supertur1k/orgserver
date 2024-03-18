package com.medicine.orgserver.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "medicaments")
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

}
