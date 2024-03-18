package com.medicine.orgserver.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users_roles")
public class UsersRoles {


    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}
