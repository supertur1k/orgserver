package com.medicine.orgserver.repositories;

import com.medicine.orgserver.entities.Role;
import com.medicine.orgserver.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
