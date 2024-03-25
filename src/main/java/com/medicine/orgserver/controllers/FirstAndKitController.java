package com.medicine.orgserver.controllers;


import com.medicine.orgserver.dto.AddMedIntoFirstAndKitDTO;
import com.medicine.orgserver.services.FirstAndKitMedicamentsService;
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
    private final FirstAndKitMedicamentsService userMedicamentsService;


    @GetMapping("/getMedicamentsForFirstAndKitById")
    public ResponseEntity<?> allUserMedicamentsByNameOfTheUser(@RequestParam Long id) {
        return userMedicamentsService.getMedicamentsCollectionByFirstAndKitId(id);
    }

    @PostMapping("/addMedicamentIntoFirstAndKitById")
    public ResponseEntity<?> setMedicamentToUser(@RequestBody AddMedIntoFirstAndKitDTO addMedToFirstAndKitDTO) {
        return userMedicamentsService.addMedicamentIntoFirstAndKitById(addMedToFirstAndKitDTO);
    }

    @DeleteMapping("/hardDeleteMedicamentForUser")
    public ResponseEntity<?> deleteMedicamentForUser(@RequestParam Long id, String medicament_name) {
        return userMedicamentsService.deleteMedicamentForUser(id, medicament_name);
    }
}
