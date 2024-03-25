package com.medicine.orgserver.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Data
@Table(name = "first_and_kit")
public class FirstAndKit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    private String name_of_the_first_and_kit;

    @Column
    private String description;

    @ManyToMany
    @JoinTable(
            name="first_and_kit_medicaments",
            joinColumns = @JoinColumn(name = "first_and_kit_id"),
            inverseJoinColumns = @JoinColumn(name = "medicament_id")
    )
    private Collection<Medicament> medicaments;

}
