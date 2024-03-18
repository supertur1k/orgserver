package com.medicine.orgserver.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users_medicaments")
public class UsersMedicaments {


    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "medicament_id", nullable = false)
    private Medicament medicament;

}
