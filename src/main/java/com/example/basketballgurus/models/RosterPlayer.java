package com.example.basketballgurus.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roster_player")
@AllArgsConstructor
@NoArgsConstructor
public class RosterPlayer {

    @Id
    @Column(nullable = false)
    @Getter @Setter
    private int id;

    @Column(nullable = false)
    @Getter @Setter
    private int playerId;

    @Column(name = "roster_id", nullable = false)
    @Getter @Setter
    private int rosterId;

    @Column(name = "is_active", nullable = false)
    @Getter @Setter
    private Boolean isActive;

}