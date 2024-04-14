package com.medicine.orgserver.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "notifications")
public class Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long idOfNotification;

    @Column(nullable = false)
    private Long idOfTheSchedule;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column
    private String comment;

    @Column(nullable = false)
    private String amount;

    @Column(nullable = false)
    private LocalDate dayOfTheWeek;

    @Column(nullable = false)
    private String time;

}
