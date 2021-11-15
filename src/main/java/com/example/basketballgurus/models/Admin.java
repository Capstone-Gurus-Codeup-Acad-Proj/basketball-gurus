package com.example.basketballgurus.models;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Table(name="admin")
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String adminusername;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String firstName;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String lastName;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String email;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String adminpassword;

    @Column
    @Getter @Setter
    private String role;

    @Column(nullable = false)
    @Getter @Setter
    private boolean isActive;


    public Admin(Admin copy) {
        id = copy.id;
        email = copy.email;
        firstName = copy.firstName;
        lastName = copy.lastName;
        adminusername = copy.adminusername;
        adminpassword = copy.adminpassword;
        role = copy.role;
        isActive = copy.isActive;

    }
}