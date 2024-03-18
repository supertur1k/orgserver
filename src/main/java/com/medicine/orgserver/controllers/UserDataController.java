package com.medicine.orgserver.controllers;


import com.medicine.orgserver.dto.AddMedToUserDTO;
import com.medicine.orgserver.services.UserMedicamentsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "user_data")
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserDataController {
    private final UserMedicamentsService userMedicamentsService;


    @GetMapping("/getMedicamentsForUser")
    public ResponseEntity<?> allUserMedicamentsByNameOfTheUser(@RequestParam String name) {
        return userMedicamentsService.getMedicamentsCollectionByUserName(name);
    }

    @PostMapping("/setMedicamentInUserMedicamentsStorage")
    public ResponseEntity<?> setMedicamentToUser(@RequestBody AddMedToUserDTO addMedToUserDTO) {
        return userMedicamentsService.setMedicamentToUserByName(addMedToUserDTO);
    }

    @DeleteMapping("/hardDeleteMedicamentForUser")
    public ResponseEntity<?> deleteMedicamentForUser(@RequestParam String username, String medicament_name) {
        return userMedicamentsService.deleteMedicamentForUser(username, medicament_name);
    }
}
