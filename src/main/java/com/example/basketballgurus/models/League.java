package com.example.basketballgurus.models;

import lombok.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="leagues")
@AllArgsConstructor
@NoArgsConstructor
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int id;

    @OneToOne
    @Getter @Setter
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User ownerId;

    @Column(nullable = false)
    @Getter @Setter
    private Date startDate;


    public String getFormattedStartDate() {
        String pattern = "mm-d-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String date =  sdf.format(startDate);
        return date;
    }

    @Column(nullable = false)
    @Getter @Setter
    private Date endDate;

    @Column(nullable = false)
    @Getter @Setter
    private String uuid = UUID.randomUUID().toString();

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String leagueName;

    @Column(nullable = false)
    @Getter @Setter
    private String password;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String leagueDifficulty;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String leaguePfp;

}