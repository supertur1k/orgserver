package com.medicine.orgserver.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@IdClass(FirstAndKitUserId.class)
@Table(name = "first_and_kit_user")
public class FirstAndKitUser {

    @Id
    @ManyToOne
    @JoinColumn(name = "first_and_kit_id", nullable = false)
    private FirstAndKit first_and_kit_id;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

}
