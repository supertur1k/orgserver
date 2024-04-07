package com.medicine.orgserver.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@IdClass(FirstAidKitMedicamentId.class)
@Table(name = "first_aid_kit_medicaments")
public class FirstAndKitMedicaments {

    @Id
    @ManyToOne
    @JoinColumn(name = "first_aid_kit_id", nullable = false)
    private FirstAidKit first_aid_kit_id;

    @Id
    @ManyToOne
    @JoinColumn(name = "medicament_id", nullable = false)
    private Medicament medicament;

}
