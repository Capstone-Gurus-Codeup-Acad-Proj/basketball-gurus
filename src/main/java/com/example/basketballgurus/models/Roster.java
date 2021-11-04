package com.example.basketballgurus.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="rosters")
@AllArgsConstructor
@NoArgsConstructor
public class Roster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String name;

    @ManyToOne
    @JoinColumn(name = "league_id", referencedColumnName = "id")
    @Getter @Setter
    private League leagueId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Getter @Setter
    private User userId;


}
