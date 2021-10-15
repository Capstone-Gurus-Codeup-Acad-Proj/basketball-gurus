package com.example.basketballgurus.models;


import lombok.*;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="history")
@NoArgsConstructor
@AllArgsConstructor
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @OneToOne
    @JoinColumn(name = "roster_player_id", referencedColumnName = "id")
    @Getter @Setter
    private RosterPlayer rosterPlayerId;

    @Column(nullable = false)
    @Getter @Setter
    private Date weekDate;

    @Column(nullable = false)
    @Getter @Setter
    private Boolean wasActive;

}