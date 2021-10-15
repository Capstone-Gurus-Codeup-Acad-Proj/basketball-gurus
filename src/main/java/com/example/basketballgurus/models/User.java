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

    @Column
    @Getter @Setter
    private String role;

//    Below is for Admin function JJM
    @Column(nullable = false)
    @Getter @Setter
    private boolean isActive;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                 ", username='" + userName + '\'' +
                 ", password='" + password + '\'' +
                 ", role='" + role + '\'' +
                '}';
    }

    public User(User copy) {
        id = copy.id;
        email = copy.email;
        userName = copy.userName;
        password = copy.password;
        firstName = copy.firstName;
        lastName = copy.lastName;
        bio = copy.bio;
        profilePicture = copy.profilePicture;
        bannerUrl = copy.bannerUrl; //changed to bannerUrl as application could "not resolve banner" JJM
        isActive = copy.isActive;
        //role = copy.role; JJM
    }
}