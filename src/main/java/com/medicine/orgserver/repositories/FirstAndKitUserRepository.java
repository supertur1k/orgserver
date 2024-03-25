package com.medicine.orgserver.repositories;

import com.medicine.orgserver.entities.FirstAndKit;
import com.medicine.orgserver.entities.FirstAndKitUser;
import com.medicine.orgserver.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface FirstAndKitUserRepository extends JpaRepository<FirstAndKitUser, Long> {

    @Query("SELECT fak FROM FirstAndKitUser fak WHERE fak.first_and_kit_id = :firstAndKitId")
    List<FirstAndKitUser> findByFirstAndKitId(FirstAndKit firstAndKitId);
}