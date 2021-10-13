package com.example.basketballgurus.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="leagues")
@AllArgsConstructor
@NoArgsConstructor
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Column(nullable = false)
    @Getter @Setter
    private int ownerId;

    @Column(nullable = false)
    @Getter @Setter
    private Date startDate;

    @Column(nullable = false)
    @Getter @Setter
    private Date endDate;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String leagueName;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String leagueDifficulty;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String leaguePfp;

}