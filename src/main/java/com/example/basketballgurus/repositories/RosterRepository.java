package com.example.basketballgurus.repositories;

import com.example.basketballgurus.models.League;
import com.example.basketballgurus.models.Roster;
import com.example.basketballgurus.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RosterRepository extends JpaRepository<Roster, Integer> {

    List<Roster> getByLeagueId(League leagueId);


}
