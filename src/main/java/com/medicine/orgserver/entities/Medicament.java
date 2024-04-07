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
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="releaseForm")
    private String releaseForm;

    @Column(name="amount")
    private String amount;

    @Column(name="directionsForUse", length = 100000)
    private String directionsForUse;

    @Column(name="indicationsForUse", length = 100000)
    private String indicationsForUse;

    @Column(name="contraindications", length = 100000)
    private String contraindications;

    @Column(name="barcode")
    private String barcode;


}
