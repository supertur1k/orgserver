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
import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor ()
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final NotificationRepository notificationRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Transactional
    public ResponseEntity<?> createScheduleForUser(ScheduleDTO scheduleDTO) {
        if (this.findByUsername(scheduleDTO.getUsername()).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пользователь не найден"), HttpStatus.BAD_REQUEST);
        }
        User user = this.findByUsername(scheduleDTO.getUsername()).get();

        if (scheduleDTO.getUsername() == null || scheduleDTO.getUsername().isBlank()
        || scheduleDTO.getName() == null || scheduleDTO.getName().isBlank()
        || scheduleDTO.getDuration() == null || scheduleDTO.getDuration() == 0
        || scheduleDTO.getAmount() == null
        || scheduleDTO.getDaysOfWeeks() == null || scheduleDTO.getDaysOfWeeks().size() < 1
        || scheduleDTO.getTimes() == null || scheduleDTO.getTimes().size() < 1) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "не все поля переданы"), HttpStatus.BAD_REQUEST);
        }
        Schedule schedule = new Schedule();
        schedule.setUsername(scheduleDTO.getUsername());
        schedule.setName(scheduleDTO.getName());
        schedule.setAmount("" + scheduleDTO.getAmount());
        schedule.setComment(scheduleDTO.getComment());
        schedule.setDuration(scheduleDTO.getDuration());
        schedule.setDaysOfWeeks(scheduleDTO.getDaysOfWeeks());
        schedule.setTimes(scheduleDTO.getTimes());
        schedule.setStartDate(LocalDate.now().toString());
        scheduleRepository.save(schedule);
        Long scheduleId = schedule.getIdOfSchedule();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH);

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(scheduleDTO.getDuration());
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            if (scheduleDTO.getDaysOfWeeks().contains(currentDate.format(formatter).toLowerCase())) {
                LocalDate finalCurrentDate = currentDate;
                scheduleDTO.getTimes().forEach(x -> {
                    Notification notification = new Notification();
                    notification.setIdOfTheSchedule(scheduleId);
                    notification.setUsername(scheduleDTO.getUsername());
                    notification.setName(scheduleDTO.getName());
                    notification.setAmount("" + scheduleDTO.getAmount());
                    notification.setComment(scheduleDTO.getComment());
                    notification.setDayOfTheWeek(finalCurrentDate);
                    notification.setTime(x);
                    notificationRepository.save(notification);
                });
            }
            currentDate = currentDate.plusDays(1);
        }

        return ResponseEntity.ok(scheduleRepository.findByUsername(user.getUsername()));
    }

    @Transactional
    public ResponseEntity<?> getSchedulesForUser(String username) {
        if (this.findByUsername(username).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Пользователь не найден"), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(scheduleRepository.findByUsername(username));
    }

    @Transactional
    public ResponseEntity<?> deleteScheduleForUser(Long idOfSchedule) {
        if (scheduleRepository.findById(idOfSchedule).isEmpty()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(),
                    "Не найдена запись с id = " + idOfSchedule), HttpStatus.BAD_REQUEST);
        }

        String username = scheduleRepository.findById(idOfSchedule).get().getUsername();
        scheduleRepository.deleteDaysOfWeeksByScheduleId(idOfSchedule);
        scheduleRepository.deleteTimesByScheduleId(idOfSchedule);
        scheduleRepository.deleteById(idOfSchedule);

        notificationRepository.deleteAllByIdOfTheSchedule(idOfSchedule);

        return ResponseEntity.ok(scheduleRepository.findByUsername(username));
    }


}
