package com.medicine.orgserver.controllers;


import com.medicine.orgserver.dto.FirstAidKitDTO;
import com.medicine.orgserver.dto.FirstAidKitIdUsernameDTO;
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
        return userService.getFirstAidKitsByUsername(username);
    }


    @PostMapping("/creteFirstAndKitForUser")
    @Operation(summary = "Создание аптечки для пользователя")
    public ResponseEntity<?> createFirstAndKitForUser(@RequestBody FirstAidKitDTO firstAidKitDTO) {
        return userService.createFirstAidKitForUser(firstAidKitDTO);
    }

    @DeleteMapping("/removeFirstAndFromForUser")
    @Operation(summary = "Отключаем аптечку от пользователя",
    description = "Если у аптечки нет других пользователей, она сотрется из хранилища.")
    public ResponseEntity<?> removeFirstAndFromForUser(@RequestBody FirstAidKitIdUsernameDTO firstAidKitIdUsernameDTO) {
        return userService.removeFirstAidFromForUser(firstAidKitIdUsernameDTO);
    }

    @PostMapping("/addExistingFirstAidKitToUser")
    @Operation(summary = "Подключить пользователя к существующей аптечке")
    public ResponseEntity<?> addAanExistingFirstAidKitToUser(@RequestBody FirstAidKitIdUsernameDTO firstAidKitIdUsernameDTO) {
        return userService.addExistingFirstAidKitToUser(firstAidKitIdUsernameDTO);
    }
}
