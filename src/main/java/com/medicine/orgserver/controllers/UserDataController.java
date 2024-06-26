package com.medicine.orgserver.controllers;


import com.medicine.orgserver.dto.*;
import com.medicine.orgserver.services.NotificationService;
import com.medicine.orgserver.services.ScheduleService;
import com.medicine.orgserver.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getUsersOfFaks")
    @Operation(summary = "Получить пользователей аптечек",
            description = "Возвращает список пользователей приложения, подключенных к переданным аптечкам.")
    public ResponseEntity<?> getUsersOfFaks(@RequestBody ListFaksDto ids) {
        return userService.getUsersOfFaks(ids);
    }

    @PostMapping("/getNotificationsOfAllUsersOfFaks")
    @Operation(summary = "Возвращает список прочитанных уведомлений всех пользователей аптечек",
            description = "Для всех переданных аптечек смотрит владельцев, возвращает список всех прочитанных уведомлений по всем пользователям.")
    public ResponseEntity<?> getNotificationsOfAllUsersOfFaks(@RequestBody ListFaksDto ids) {
        return userService.getNotificationsOfAllUsersOfFaks(ids);
    }

    @PostMapping("/addExistingFirstAidKitToUser")
    @Operation(summary = "Подключить пользователя к существующей аптечке")
    public ResponseEntity<?> addAanExistingFirstAidKitToUser(@RequestBody FirstAidKitIdUsernameDTO firstAidKitIdUsernameDTO) {
        return userService.addExistingFirstAidKitToUser(firstAidKitIdUsernameDTO);
    }

    @PostMapping("/createNotificationInviteToFak")
    @Operation(summary = "Создать уведомление с приглашением к аптечке")
    public ResponseEntity<?> createNotificationInviteToFak(@RequestBody FirstAidKitIdUsernameDTO2Users firstAidKitIdUsernameDTO2Users) {
        return userService.notificationForAddingExistingFirstAidKitToUser(firstAidKitIdUsernameDTO2Users);
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

    @PostMapping("/readNotification")
    @Operation(summary = "Пометить нотификацию как прочитанную по id")
    public ResponseEntity<?> markNotificationAsRead(@RequestParam Long id, String username) {
        return notificationService.readNotification(id, username);
    }



}
