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

    @Column
    private Long idOfTheSchedule;

    @Column(nullable = false)
    private String username;

    @Column
    private String name;

    @Column
    private String comment;

    @Column
    private String amount;

    @Column
    private LocalDate dayOfTheWeek;

    @Column
    private String time;

    @Column
    private Boolean received = false;

}
