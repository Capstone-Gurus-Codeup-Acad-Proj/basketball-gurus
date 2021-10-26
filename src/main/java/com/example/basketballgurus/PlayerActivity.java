package com.example.basketballgurus;

import com.example.basketballgurus.models.History;
import com.example.basketballgurus.models.League;
import com.example.basketballgurus.models.Roster;
import com.example.basketballgurus.models.RosterPlayer;
import com.example.basketballgurus.repositories.HistoryRepository;
import com.example.basketballgurus.repositories.LeaguesRepository;
import com.example.basketballgurus.repositories.RosterPlayerRepository;
import com.example.basketballgurus.repositories.RosterRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class PlayerActivity {

    private final HistoryRepository historyDao;
    private final LeaguesRepository leagueDao;
    private final RosterRepository rosterDao;
    private final RosterPlayerRepository rosterPlayerDao;

    public PlayerActivity(HistoryRepository historyDao, LeaguesRepository leagueDao, RosterRepository rosterDao, RosterPlayerRepository rosterPlayerDao) {
        this.historyDao = historyDao;
        this.leagueDao = leagueDao;
        this.rosterDao = rosterDao;
        this.rosterPlayerDao = rosterPlayerDao;
    }
    @Scheduled(cron = "* */3 * * * ?")
    public void checkStatus() throws ParseException {

        List<History> currentWeeksHistory = getWeeksScore();

        if (currentWeeksHistory.size() == 0){
            createHistories();
        }

    }

    @Scheduled(cron = "0 0 0 * * MON", zone = "America/Chicago")
    public void createHistories() throws ParseException {

        List<History> currentWeeksHistory = getWeeksScore();

        if(currentWeeksHistory.size() == 0){
            saveHistories();
        }

    }

    public List<History> getWeeksScore() throws ParseException {

        LocalDate monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date convertedMonday = formatter.parse(monday.getDayOfMonth() + "-" + monday.getMonthValue() + "-" + monday.getYear());

        return historyDao.getByWeekDate(convertedMonday);

    }

    public void saveHistories() throws ParseException {

        List<League> allLeagues = leagueDao.findAll();

        for (League league : allLeagues){

            LocalDate monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date convertedMonday = formatter.parse(monday.getDayOfMonth() + "-" + monday.getMonthValue() + "-" + monday.getYear());

            if (league.getEndDate().after(convertedMonday) || league.getEndDate().equals(convertedMonday)){

                List<Roster> rosters = rosterDao.getByLeagueId(league);

                for (Roster roster : rosters){

                    List<RosterPlayer> players = rosterPlayerDao.getByRosterId(roster);

                    for (RosterPlayer rp : players){

                        if (rp.getIsActive()){
                            History history = new History(0, rp, convertedMonday,  true);
                            historyDao.save(history);
                        }
                    }
                }
            }
        }
    }
}
