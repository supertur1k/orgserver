package com.medicine.orgserver.repositories;

import com.medicine.orgserver.entities.FirstAndKit;
import com.medicine.orgserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FirstAndKitRepository extends JpaRepository<FirstAndKit, Long> {
    Optional<FirstAndKit> findById(Long id);
}
