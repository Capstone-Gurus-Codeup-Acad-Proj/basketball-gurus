package com.example.basketballgurus.models;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="weeks_games")
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @Column(nullable = false, name = "id")
    @Getter @Setter
    private int id;

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

    @Column(nullable = false, columnDefinition = "Boolean default false")
    @Getter @Setter
    private Boolean finished;

    @Column(nullable = false, columnDefinition = "Boolean default false")
    @Getter @Setter
    private Boolean recorded;

    }