package com.medicine.orgserver.repositories;

import com.medicine.orgserver.entities.FirstAidKit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FirstAidKitRepository extends JpaRepository<FirstAidKit, Long> {
    Optional<FirstAidKit> findById(Long id);
}
