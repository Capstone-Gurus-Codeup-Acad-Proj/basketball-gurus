package com.example.basketballgurus.models;

import lombok.*;
import javax.persistence.*;

@Entity
@ToString
@Table(name="admin_users")
@AllArgsConstructor
@NoArgsConstructor
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(length = 100)
    @Getter @Setter
    private String adminUsername;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String adminPassword;

    @Column
    @Getter @Setter
    private String role;


    public AdminUser(AdminUser copy) {
        id = copy.id;
        adminUsername = copy.adminUsername;
        adminPassword = copy.adminPassword;

    }

}