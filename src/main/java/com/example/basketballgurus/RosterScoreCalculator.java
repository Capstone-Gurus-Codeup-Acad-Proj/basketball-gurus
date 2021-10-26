package com.example.basketballgurus;

import com.example.basketballgurus.models.History;
import com.example.basketballgurus.models.PlayerScore;
import com.example.basketballgurus.models.Roster;
import com.example.basketballgurus.models.RosterPlayer;
import com.example.basketballgurus.repositories.HistoryRepository;
import com.example.basketballgurus.repositories.LeaguesRepository;
import com.example.basketballgurus.repositories.RosterPlayerRepository;
import com.example.basketballgurus.repositories.ScoreRepository;
import com.example.basketballgurus.services.RosterScoreService;
import org.springframework.stereotype.Service;

import javax.servlet.http.PushBuilder;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;

@Service
public class RosterScoreCalculator implements RosterScoreService {

    private final RosterPlayerRepository rosterPlayerDao;
    private final ScoreRepository scoreRepositoryDao;
    private final HistoryRepository historyDao;

    public RosterScoreCalculator(RosterPlayerRepository rosterPlayerDao, ScoreRepository scoreRepositoryDao, HistoryRepository historyDao) {
        this.rosterPlayerDao = rosterPlayerDao;
        this.scoreRepositoryDao = scoreRepositoryDao;
        this.historyDao = historyDao;
    }

    public int getWeeksScore(Roster roster){

        List<RosterPlayer> rp = rosterPlayerDao.getByRosterId(roster);

        int total = 0;

        for (RosterPlayer player : rp){

            if (player.getIsActive()){
                System.out.println("active");

                List<PlayerScore> scores = scoreRepositoryDao.getByPlayerId(player.getPlayerId());

                for (PlayerScore score : scores){
                    if (checkDate(score)){
                        System.out.println("this week");
                        total += score.getScore();
                    }
                }
            }
        }
        return total;
    }

    public HashMap<Date,Integer> getHistoricalScore(Roster roster, java.util.Date startDate, java.util.Date endDate) {

        HashMap<Date, Integer> scoreMap = seedHashMap(startDate, endDate);

        List<RosterPlayer> rp = rosterPlayerDao.getByRosterId(roster);

        for (RosterPlayer player : rp) {
            List<History> histories = historyDao.getByRosterPlayerId(player);

            for (History history : histories) {
                if (history.getWasActive() && (startDate.before(history.getWeekDate()) || startDate.equals(history.getWeekDate())) && (endDate.after(history.getWeekDate()) || endDate.equals(history.getWeekDate()))) {
                    int weekTotal = 0;

                    List<PlayerScore> scores = scoreRepositoryDao.getByPlayerId(player.getPlayerId());

                    for(PlayerScore score : scores){
                        if (checkWeekDate(history.getWeekDate(), score.getGameDate())){
                            weekTotal += score.getScore();
                        }
                    }
                    Date weekDate = new Date(history.getWeekDate().getTime());
                    scoreMap.put(weekDate, scoreMap.get(weekDate) + weekTotal);
                }
            }

        }
        return scoreMap;
    }


    public boolean checkWeekDate(java.util.Date historyDate, Date scoreDate){

        LocalDate date = Instant.ofEpochMilli(scoreDate.getTime()).atZone(ZoneId.of("UTC")).toLocalDate();
        LocalDate monday = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate weekDate = Instant.ofEpochMilli(historyDate.getTime()).atZone(ZoneId.of("UTC")).toLocalDate();

        return monday.isEqual(weekDate);


    }

    public HashMap<Date,Integer> seedHashMap(java.util.Date startDate, java.util.Date endDate){

        HashMap<Date, Integer> scoreMap = new HashMap<>();
        Date monday = new Date(startDate.getTime());

        do{
            scoreMap.put(monday, 0);
            monday = new Date(monday.getTime() + 604800000);
        } while (monday.before(endDate));

        return scoreMap;
    }

    private Boolean checkDate(PlayerScore score){

        LocalDate date = LocalDate.now(ZoneId.of("America/Chicago"));
        LocalDate monday = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate sunday = date.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        java.util.Date date1 = score.getGameDate();
        Date input = new Date(date1.getTime());

        LocalDate gameDate = input.toLocalDate();
        return monday.isBefore(gameDate) && sunday.isAfter(gameDate) || monday.isEqual(gameDate) || sunday.isEqual(gameDate);

    }
}
