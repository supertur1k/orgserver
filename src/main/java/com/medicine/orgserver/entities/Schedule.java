package com.medicine.orgserver.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "schedules")
public class Schedule {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long idOfSchedule;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String name;

    @Column
    private String comment;

    @Column(nullable = false)
    private String amount;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private String startDate;

    @ElementCollection
    @CollectionTable(name = "entity_days_of_weeks")
    @Column(name = "day_of_week")
    private List<String> daysOfWeeks;

    @ElementCollection
    @CollectionTable(name = "entity_times")
    @Column(name = "time")
    private List<String> times;

}
