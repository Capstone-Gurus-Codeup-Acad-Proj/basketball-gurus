package com.example.basketballgurus.repositories;

import com.example.basketballgurus.models.Roster;
import com.example.basketballgurus.models.RosterPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RosterPlayerRepository extends JpaRepository<RosterPlayer, Integer> {

    List<RosterPlayer> getByRosterId(Roster rosterId);

}
