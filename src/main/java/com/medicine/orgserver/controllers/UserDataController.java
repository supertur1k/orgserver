package com.medicine.orgserver.controllers;


import com.medicine.orgserver.dto.AddMedicamentIntoFAKBarcode;
import com.medicine.orgserver.dto.FirstAidKitDTO;
import com.medicine.orgserver.dto.FirstAidKitIdUsernameDTO;
import com.medicine.orgserver.dto.ScheduleDTO;
import com.medicine.orgserver.services.NotificationService;
import com.medicine.orgserver.services.ScheduleService;
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
    private final ScheduleService scheduleService;
    private final NotificationService notificationService;

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

    @PostMapping("/createNotificationInviteToFak")
    @Operation(summary = "Создать уведомление с приглашением к аптечке")
    public ResponseEntity<?> createNotificationInviteToFak(@RequestBody FirstAidKitIdUsernameDTO firstAidKitIdUsernameDTO) {
        return userService.notificationForAddingExistingFirstAidKitToUser(firstAidKitIdUsernameDTO);
    }

    @PostMapping("/createSchedule")
    @Operation(summary = "Создать график приема лекартсва")
    public ResponseEntity<?> createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return scheduleService.createScheduleForUser(scheduleDTO);
    }

    @GetMapping("/getSchedulesForUser")
    @Operation(summary = "Создать график приема лекартсва")
    public ResponseEntity<?> getSchedule(@RequestParam String username) {
        return scheduleService.getSchedulesForUser(username);
    }

    @DeleteMapping("/deleteSchedule")
    @Operation(summary = "Удалить график преима лекарств")
    public ResponseEntity<?> deleteSchedule(@RequestParam Long id) {
        return scheduleService.deleteScheduleForUser(id);
    }

    @GetMapping("/getNotifications")
    @Operation(summary = "Получить список нотификаций для пользователя")
    public ResponseEntity<?> getNotifications(@RequestParam String username) {
        return notificationService.getNotificationsForUser(username);
    }

    @DeleteMapping("/deleteNotification")
    @Operation(summary = "Удалить нотификацию по id")
    public ResponseEntity<?> deleteNotification(@RequestParam Long id) {
        return notificationService.deleteNotificationForUser(id);
    }



}
