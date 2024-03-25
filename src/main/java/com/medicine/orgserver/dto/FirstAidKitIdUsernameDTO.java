package com.medicine.orgserver.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FirstAidKitIdUsernameDTO {
    String username;
    Long first_aid_kit_id;
}
