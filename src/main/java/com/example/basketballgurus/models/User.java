package com.example.basketballgurus.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String userName;

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
    private String password;

    @Column
    @Getter @Setter
    private String profilePicture;

    @Column
    @Getter @Setter
    private String banner;

    @Column
    @Getter @Setter
    private String bio;
    @Column(nullable = false)
    private boolean isActive;


}