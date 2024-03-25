package com.medicine.orgserver.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "first_and_kit_medicaments")
public class FirstAndKitMedicaments {

    @Id
    @ManyToOne
    @JoinColumn(name = "first_and_kit_id", nullable = false)
    private FirstAndKit first_and_kit_id;

    @Id
    @ManyToOne
    @JoinColumn(name = "medicament_id", nullable = false)
    private Medicament medicament;

}
