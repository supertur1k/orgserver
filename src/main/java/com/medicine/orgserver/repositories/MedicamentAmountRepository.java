package com.medicine.orgserver.repositories;

import com.medicine.orgserver.entities.FirstAidKit;
import com.medicine.orgserver.entities.MedicamentAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicamentAmountRepository extends JpaRepository<MedicamentAmount, Long> {
    Optional<MedicamentAmount> findByIdOfFakAndNameOfMedicament(Long id_of_fak, String nameOfMedicament);

    @Modifying
    @Query("delete from MedicamentAmount m where m.idOfFak = ?1 and m.nameOfMedicament = ?2")
    void deleteByIdOfFakAndNameOfMedicament(Long id_of_fak, String nameOfMedicament);
}

