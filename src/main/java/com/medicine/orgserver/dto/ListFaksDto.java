package com.medicine.orgserver.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListFaksDto {
    List<Long> first_aid_kit_ids;
}
