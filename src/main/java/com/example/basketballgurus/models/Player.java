package com.example.basketballgurus.models;
import lombok.*;

import javax.persistence.*;

import java.util.Date;
@Entity
@Table(name="players")
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    @Column(nullable = false)
    @Getter @Setter
    private int id;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String firstName;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String lastName;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String teamName;

    @OneToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team teamId;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String position;

    @Column(nullable = true)
    @Getter @Setter
    private Date dob;

    @Column
    @Getter @Setter
    private int height;

    @Column
    @Getter @Setter
    private int weight;

    @Column
    @Getter @Setter
    private int jerseyNumber;

    @Column
    @Getter @Setter
    private int age;

    @Column(nullable = false)
    @Getter @Setter
    private double point;

    @Column(nullable = false)
    @Getter @Setter
    private double blocks;

    @Column(nullable = false)
    @Getter @Setter
    private double assists;

    @Column(nullable = false)
    @Getter @Setter
    private double rebounds;

    @Column(nullable = false)
    @Getter @Setter
    private double turnOvers;

    @Column(nullable = false)
    @Getter @Setter
    private float percentThrees;

    @Column(nullable = false)
    @Getter @Setter
    private float percentFreethrows;

    @Column(nullable = false)
    @Getter @Setter
    private String headshotUrl;

    @Column(nullable = false)
    @Getter @Setter
    private int price;

    @Column(nullable = false)
    @Getter @Setter
    private int percentFieldGoals;

}