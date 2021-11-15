package com.example.basketballgurus.models;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String username;

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
<<<<<<< HEAD

    @Column
=======
    @Column()
>>>>>>> 12cff3f637feb317163226bab761b2dc7789e0e2
    @Getter @Setter
    private String bannerUrl;

    @Column
    @Getter @Setter
    private String bio;

    @Column
    @Getter @Setter
    private String role;

    @Column(nullable = false)
    @Getter @Setter
    private boolean isActive;


    public User(User copy) {
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
        firstName = copy.firstName;
        lastName = copy.lastName;

    }
}