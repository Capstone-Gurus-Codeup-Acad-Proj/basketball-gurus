package com.example.basketballgurus;

import com.example.basketballgurus.models.League;
import com.example.basketballgurus.models.Roster;
import com.example.basketballgurus.repositories.HistoryRepository;
import com.example.basketballgurus.repositories.RosterPlayerRepository;
import com.example.basketballgurus.repositories.RosterRepository;
import com.example.basketballgurus.repositories.ScoreRepository;
import com.example.basketballgurus.services.LeagueScoreService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;

@Service
public class LeagueScoreCalculator implements LeagueScoreService {

    private final RosterRepository rosterDao;
    private final RosterPlayerRepository rosterPlayerDao;
    private final ScoreRepository scoreRepositoryDao;
    private final HistoryRepository historyDao;

    public LeagueScoreCalculator(RosterRepository rosterDao, RosterPlayerRepository rosterPlayerDao, ScoreRepository scoreRepositoryDao, HistoryRepository historyDao) {
        this.rosterDao = rosterDao;
        this.rosterPlayerDao = rosterPlayerDao;
        this.scoreRepositoryDao = scoreRepositoryDao;
        this.historyDao = historyDao;
    }

    public HashMap<String, HashMap<Date, Integer>> getLeagueScores(League league){

        RosterScoreCalculator rsc = new RosterScoreCalculator(rosterPlayerDao, scoreRepositoryDao, historyDao);

        List<Roster> rosters = rosterDao.getByLeagueId(league);

        HashMap<String, HashMap<Date, Integer>> leagueRosterWeeklyScores = new HashMap<>();

        for (Roster roster : rosters){

            HashMap<Date, Integer> weeklyScores = rsc.getScore(roster, league.getStartDate(), league.getEndDate());

            leagueRosterWeeklyScores.put(roster.getName(), weeklyScores);
        }

        return leagueRosterWeeklyScores;

    }

}
