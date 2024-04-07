package com.medicine.orgserver.repositories;

import com.medicine.orgserver.entities.Medicament;
import com.medicine.orgserver.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament, Integer> {
    Optional<Medicament> findByName(String name);
    Optional<Medicament> findByBarcode(String barcode);
    Optional<Medicament> findById(Long id);
}
