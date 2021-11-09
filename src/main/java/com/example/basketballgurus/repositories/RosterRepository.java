package com.example.basketballgurus.repositories;

import com.example.basketballgurus.models.League;
import com.example.basketballgurus.models.Roster;
import com.example.basketballgurus.models.Team;
import com.example.basketballgurus.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RosterRepository extends JpaRepository<Roster, Integer> {

    List<Roster> getByLeagueId(League leagueId);

    List<Roster> getByUserId(User user);

    Optional<Roster> getRosterByLeagueIdAndUserId(League leagueId, User userId);


}
