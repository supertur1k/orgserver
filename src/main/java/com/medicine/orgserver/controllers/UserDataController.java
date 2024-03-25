package com.medicine.orgserver.controllers;


import com.medicine.orgserver.dto.FirstAndKitDTO;
import com.medicine.orgserver.dto.FirstAndKitIdUsernameDTO;
import com.medicine.orgserver.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    private final UserService userService;

    @GetMapping("/getFirstAndKitsByUsername")
    public ResponseEntity<?> getFirstAndKitsByUsername(@RequestParam String username) {
        return userService.getFirstAndKitsByUsername(username);
    }


    @PostMapping("/creteFirstAndKitForUser")
    @Operation(summary = "Создание аптечки для пользователя")
    public ResponseEntity<?> createFirstAndKitForUser(@RequestBody FirstAndKitDTO firstAndKitDTO) {
        return userService.createFirstAndKitForUser(firstAndKitDTO);
    }

    @DeleteMapping("/removeFirstAndFromForUser")
    @Operation(summary = "Отключаем аптечку от пользователя",
    description = "Если у аптечки нет других пользователей, она сотрется из хранилища.")
    public ResponseEntity<?> removeFirstAndFromForUser(@RequestBody FirstAndKitIdUsernameDTO firstAndKitIdUsernameDTO) {
        return userService.removeFirstAndFromForUser(firstAndKitIdUsernameDTO);
    }

    @PostMapping("/addExistingFirstAidKitToUser")
    @Operation(summary = "Подключить пользователя к существующей аптечке")
    public ResponseEntity<?> addAanExistingFirstAidKitToUser(@RequestBody FirstAndKitIdUsernameDTO firstAndKitIdUsernameDTO) {
        return userService.addExistingFirstAidKitToUser(firstAndKitIdUsernameDTO);
    }
}
