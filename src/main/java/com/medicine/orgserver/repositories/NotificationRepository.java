package com.medicine.orgserver.repositories;

import com.medicine.orgserver.entities.Notification;
import com.medicine.orgserver.entities.Schedule;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Long> {

    @Transactional
    Collection<Notification> findByUsername(String username);

    @Transactional
    Optional<Notification> findByIdOfNotification(Long idOfNotification);

    @Modifying
    @Transactional
    @Query("DELETE FROM Notification n WHERE n.idOfTheSchedule = :scheduleId")
    void deleteAllByIdOfTheSchedule(Long scheduleId);

    @Transactional
    void deleteByIdOfNotification(Long idOfNotification);
}
