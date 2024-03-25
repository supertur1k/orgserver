package com.medicine.orgserver.dto;

import com.medicine.orgserver.entities.Medicament;
import com.medicine.orgserver.entities.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FirstAndKitDTO {
    String username;
    String name_of_the_first_and_kit;
    String description;
}
