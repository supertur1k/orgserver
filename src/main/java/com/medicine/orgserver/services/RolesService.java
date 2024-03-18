package com.medicine.orgserver.services;

import com.medicine.orgserver.entities.Role;
import com.medicine.orgserver.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolesService {

    private final RoleRepository roleRepository;

    public Role getDefaultUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }

}
