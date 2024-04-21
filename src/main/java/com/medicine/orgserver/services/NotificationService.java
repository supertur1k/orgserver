package com.medicine.orgserver.services;

import com.medicine.orgserver.dto.ScheduleDTO;
import com.medicine.orgserver.entities.Notification;
import com.medicine.orgserver.entities.Schedule;
import com.medicine.orgserver.entities.User;
import com.medicine.orgserver.exceptions.AppError;
import com.medicine.orgserver.repositories.NotificationRepository;
import com.medicine.orgserver.repositories.ScheduleRepository;
import com.medicine.orgserver.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor ()
public class NotificationService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Transactional
    public ResponseEntity<?> getNotificationsForUser(String username) {
        if (this.findByUsername(username).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пользователь не найден"), HttpStatus.BAD_REQUEST);
        }

        Collection<Notification> notifications = notificationRepository.findByUsername(username)
                .stream().filter(x->x.getDayOfTheWeek().isBefore(LocalDate.now().plusDays(1))).collect(Collectors.toList());
        return ResponseEntity.ok(notifications);
    }

    @Transactional
    public ResponseEntity<?> deleteNotificationForUser(Long idOfNotification) {
        if (notificationRepository.findByIdOfNotification(idOfNotification).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Не найдена запись с id = " + idOfNotification), HttpStatus.BAD_REQUEST);
        }

        String username = notificationRepository.findByIdOfNotification(idOfNotification).get().getUsername();
        notificationRepository.deleteByIdOfNotification(idOfNotification);

        return ResponseEntity.ok(notificationRepository.findByUsername(username));
    }


}
