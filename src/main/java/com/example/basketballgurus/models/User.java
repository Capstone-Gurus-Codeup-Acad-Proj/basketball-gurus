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

    @Column(length = 100)
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
    private String bannerUrl;

    @Column
    @Getter @Setter
    private String bio;

    @Column(nullable = false)
    @Getter @Setter
    private boolean isActive;


    public User(User copy) {
        id = copy.id;
        email = copy.email;
        userName = copy.userName;
        password = copy.password;
        firstName = copy.firstName;
        lastName = copy.lastName;
        bio = copy.bio;
        profilePicture = copy.profilePicture;
        bannerUrl = copy.bannerUrl;
        isActive = copy.isActive;
    }
}