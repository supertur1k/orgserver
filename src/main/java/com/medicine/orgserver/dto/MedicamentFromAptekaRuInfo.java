package com.medicine.orgserver.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicamentFromAptekaRuInfo {

    // форма выпуска
    String releaseForm;
    // кол-во в упаковке
    String amount;
    // Способ применения и дозы
    String directionsForUse;
    // Показания
    String indicationsForUse;
    // Противопоказания
    String contraindications;

}
