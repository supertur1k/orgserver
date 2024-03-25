package com.medicine.orgserver.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddMedIntoFirstAidKitDTO {
    Long id;
    String nameOfTheMedicament;
}
