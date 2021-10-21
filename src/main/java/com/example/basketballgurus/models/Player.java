package com.example.basketballgurus.models;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="players")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Player {

    public Player(Long id) {
        this.id = id;
    }

    @Id
    @Column(nullable = false)
    @Getter @Setter
    private Long id;

    @Column(nullable = false, length = 100)
    @Getter
    @Setter
    private String firstName;

    @Column(nullable = false, length = 100)
    @Getter
    @Setter
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    @Getter @Setter

    private Team teamId;

    @Column(nullable = false, length = 100)
    @Getter
    @Setter
    private String position;

    @Column(nullable = true)
    @Getter
    @Setter
    private Date dob;

    @Column
    @Getter @Setter
    private String height;

    @Column
    @Getter @Setter
    private String weight;

    @Column
    @Getter @Setter
    private String jerseyNumber;

    @Column
    @Getter
    @Setter
    private int age;

    @Column(nullable = false)
    @Getter
    @Setter
    private double point;

    @Column(nullable = false)
    @Getter
    @Setter
    private double blocks;

    @Column(nullable = false)
    @Getter
    @Setter
    private double assists;

    @Column(nullable = false)
    @Getter
    @Setter
    private double rebounds;

    @Column(nullable = false)
    @Getter
    @Setter
    private double turnOvers;

    @Column(nullable = false)
    @Getter
    @Setter
    private float percentThrees;

    @Column(nullable = false)
    @Getter
    @Setter
    private float percentFreethrows;

    @Column(nullable = false)
    @Getter
    @Setter
    private String headshotUrl;

    @Column(nullable = false)
    @Getter
    @Setter
    private int price;

    @Column(nullable = false)
    @Getter @Setter
    private int percentFieldGoals;


}