package com.example.basketballgurus.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roster_player")
@AllArgsConstructor
@NoArgsConstructor
public class RosterPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @Getter @Setter
    private Player playerId;

    @ManyToOne
    @JoinColumn(name = "roster_id", referencedColumnName = "id")
    @Getter @Setter
    private Roster rosterId;

    @Column(name = "is_active", nullable = false)
    @Getter @Setter
    private Boolean isActive;

}