package com.medicine.orgserver.controllers;


import com.medicine.orgserver.dto.AddMedIntoFirstAidKitDTO;
import com.medicine.orgserver.dto.AddMedicamentIntoFAKBarcode;
import com.medicine.orgserver.services.FirstAidKitMedicamentsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "user_data")
@Slf4j
@RestController
@RequiredArgsConstructor
public class FirstAndKitController {
    private final FirstAidKitMedicamentsService userMedicamentsService;


    @GetMapping("/getMedicamentsForFirstAndKitById")
    public ResponseEntity<?> allUserMedicamentsByNameOfTheUser(@RequestParam Long id) {
        return userMedicamentsService.getMedicamentsCollectionByFirstAidKitId(id);
    }

    @PostMapping("/addMedicamentIntoFirstAndKitById")
    public ResponseEntity<?> setMedicamentToUser(@RequestBody AddMedIntoFirstAidKitDTO addMedToFirstAndKitDTO) {
        return userMedicamentsService.addMedicamentIntoFirstAidKitById(addMedToFirstAndKitDTO);
    }

    @PostMapping("/addMedicamentIntoFirstAndKitByBarcode")
    public ResponseEntity<?> setMedicamentToUserBarcode(@RequestBody AddMedicamentIntoFAKBarcode addMedicamentIntoFAKBarcode) {
        return userMedicamentsService.addMedicamentIntoFirstAidKitByBarcode(addMedicamentIntoFAKBarcode);
    }

    @DeleteMapping("/hardDeleteMedicamentForUser")
    public ResponseEntity<?> deleteMedicamentForUser(@RequestParam Long id, String medicament_name) {
        return userMedicamentsService.deleteMedicamentForUser(id, medicament_name);
    }
}
