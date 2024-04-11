package com.medicine.orgserver.repositories;

import com.medicine.orgserver.entities.Schedule;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    Collection<Schedule> findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM entity_days_of_weeks WHERE schedule_id_of_schedule = ?1", nativeQuery = true)
    void deleteDaysOfWeeksByScheduleId(Long scheduleId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM entity_times WHERE schedule_id_of_schedule = ?1", nativeQuery = true)
    void deleteTimesByScheduleId(Long scheduleId);
}
