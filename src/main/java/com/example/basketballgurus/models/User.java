package com.example.basketballgurus.models;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private String userName;
    @Column(nullable = false, length = 100)
    private String firstName;
    @Column(nullable = false, length = 100)
    private String lastName;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false, length = 100)
    private String password;
    @Column()
    private String profilePicture;
    @Column()
    private String banner;
    @Column()
    private String bio;
    @Column(nullable = false)
    private boolean isActive;
    public User() {
    }
    public User(String userName, String firstName, String lastName, String email, String password, String profilePicture, String banner, String bio, int id, boolean isActive) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
        this.banner = banner;
        this.bio = bio;
        this.id = id;
        this.isActive = isActive;
    }
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getProfilePicture() {return profilePicture;}
    public void setProfilePicture(String profilePicture) {this.profilePicture = profilePicture;}
    public String getBanner() {return banner;}
    public void setBanner(String banner) {this.banner = banner;}
    public String getBio() {return bio;}
    public void setBio(String bio) {this.bio = bio;}
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public boolean isActive() {return isActive;}
    public void setActive(boolean active) {isActive = active;}
}