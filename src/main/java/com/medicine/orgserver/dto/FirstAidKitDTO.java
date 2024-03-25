package com.medicine.orgserver.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FirstAidKitDTO {
    String username;
    String name_of_the_first_aid_kit;
    String description;
}
