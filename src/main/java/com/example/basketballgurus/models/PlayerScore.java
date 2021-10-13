package com.example.basketballgurus.models;
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

    @Column(nullable = false)
    @Getter @Setter
    private int player_id;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private int score;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private Date gameDate;


}
