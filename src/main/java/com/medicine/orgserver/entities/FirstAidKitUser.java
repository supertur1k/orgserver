package com.medicine.orgserver.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@IdClass(FirstAidKitUserId.class)
@Table(name = "first_aid_kit_user")
public class FirstAidKitUser {

    @Id
    @ManyToOne
    @JoinColumn(name = "first_aid_kit_id", nullable = false)
    private FirstAidKit first_aid_kit_id;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user_id;

}
