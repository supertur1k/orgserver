package com.medicine.orgserver.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleDTO {

    private String username;
    private String name;
    private String comment;
    private Object amount;
    private Integer duration;
    private List<String> daysOfWeeks;
    private List<String> times;
}
