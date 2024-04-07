package com.medicine.orgserver.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
@Table(name = "medicament_amount")
public class MedicamentAmount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private Long idOfFak;

    @Column
    private String nameOfMedicament;

    @Column
    private String amount;

}
