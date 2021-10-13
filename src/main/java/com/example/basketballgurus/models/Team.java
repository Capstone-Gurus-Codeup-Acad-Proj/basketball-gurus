package com.example.basketballgurus.models;


import com.mysql.cj.xdevapi.Collection;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="teams")
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @Column(nullable = false, name = "id")
    @Getter @Setter
    private int id;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String fullName;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String city;

    @Column(nullable = false, length = 100)
    @Getter @Setter
    private String nickname;

    @Column(nullable = false, length = 50)
    @Getter @Setter
    private String abbr;

    @Column(nullable = false)
    @Getter @Setter
    private String logoUrl;

    @OneToOne
    private Game game;
}
