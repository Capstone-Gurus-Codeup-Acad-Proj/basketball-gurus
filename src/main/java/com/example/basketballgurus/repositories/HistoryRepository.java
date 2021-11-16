package com.example.basketballgurus.repositories;

import com.example.basketballgurus.models.History;
import com.example.basketballgurus.models.RosterPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Integer> {

    List<History> getByRosterPlayerId(RosterPlayer rosterPlayerId);

    List<History> getByWeekDate(Date date);

    List<History> getByWeekDateAndRosterPlayerId(Date weekDate, RosterPlayer rosterPlayerId);

}
