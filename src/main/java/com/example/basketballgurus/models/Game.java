package com.example.basketballgurus.models;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="weeks_games")
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @Column(nullable = false)
    @Getter @Setter
    private int gameId;

    @OneToOne
    @JoinColumn(name = "home_team_id", referencedColumnName = "id")
    @Getter @Setter
    private Team homeTeamId;

    @OneToOne
    @JoinColumn(name = "away_team_id", referencedColumnName = "id")
    @Getter @Setter
    private Team awayTeamId;


    @Column(nullable = false)
    @Getter @Setter
    private Date startTime;

    }