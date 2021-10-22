package com.example.basketballgurus.models;
import com.example.basketballgurus.RestModels.GameStatsModel;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="scores")
@AllArgsConstructor
@NoArgsConstructor
public class PlayerScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player playerId;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private int score;

    @Getter @Setter
    private int gameApiId;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private Date gameDate;




}
