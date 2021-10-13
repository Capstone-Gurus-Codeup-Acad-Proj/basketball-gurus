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

    @Column(nullable = false)
    @Getter @Setter
    private int rosterPlayerId;

    @Column(nullable = false)
    @Getter @Setter
    private Date weekDate;

    @Column(nullable = false)
    @Getter @Setter
    private Boolean wasActive;

}