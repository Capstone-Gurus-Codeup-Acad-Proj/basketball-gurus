package com.example.basketballgurus.repositories;

import com.example.basketballgurus.models.League;
import com.example.basketballgurus.models.Roster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RosterRepository extends JpaRepository<Roster, Integer> {

    List<Roster> getByLeagueId(League leagueId);

}
