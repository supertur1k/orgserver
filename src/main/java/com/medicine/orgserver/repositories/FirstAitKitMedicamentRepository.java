package com.medicine.orgserver.repositories;

import com.medicine.orgserver.entities.FirstAndKitMedicaments;
import com.medicine.orgserver.entities.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirstAitKitMedicamentRepository extends JpaRepository<FirstAndKitMedicaments, Long> {

    @Query("SELECT fak FROM FirstAndKitMedicaments fak WHERE fak.medicament = :medicament_id")
    List<FirstAndKitMedicaments> findByMedicament(Medicament medicament_id);
}