package com.medicine.orgserver.repositories;

import com.medicine.orgserver.entities.FirstAidKit;
import com.medicine.orgserver.entities.FirstAidKitUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FirstAidKitUserRepository extends JpaRepository<FirstAidKitUser, Long> {

    @Query("SELECT fak FROM FirstAidKitUser fak WHERE fak.first_aid_kit_id = :firstAidKitId")
    List<FirstAidKitUser> findByFirstAidKitId(FirstAidKit firstAidKitId);
}