package com.medicine.orgserver.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FirstAidKitIdUsernameDTO2Users {
    String username;
    String inviter;
    Long first_aid_kit_id;
}
