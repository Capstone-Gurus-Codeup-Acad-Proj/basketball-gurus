package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.*;
import com.example.basketballgurus.repositories.*;
import com.example.basketballgurus.services.GameBarService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TeamController {

    private final GameRepository gameDao;
    private final GameBarService gm;
    private final PlayerRepository playerDao;
    private final TeamRepository teamDao;
    private final RosterRepository rosterDao;
    private final LeaguesRepository leagueDao;
    private final UserRepository userDao;
    private final RosterPlayerRepository rosterPlayerDao;

    public TeamController(GameRepository gameDao, GameBarService gm, PlayerRepository playerDao, TeamRepository teamDao, RosterRepository rosterDao, LeaguesRepository leagueDao, UserRepository userDao, RosterPlayerRepository rosterPlayerDao) {
        this.gameDao = gameDao;
        this.gm = gm;
        this.playerDao = playerDao;
        this.teamDao = teamDao;
        this.rosterDao = rosterDao;
        this.leagueDao = leagueDao;
        this.userDao = userDao;
        this.rosterPlayerDao = rosterPlayerDao;
    }

    @GetMapping("/team")
    public String myTeam(Model model){
        model.addAttribute("games", gm.getTodaysGames());
        return  "myTeam";
    }

    @RequestMapping(value = "/team/create", method = RequestMethod.POST)
    public String getPlayerByName(@RequestParam(name = "delete") Boolean delete, @RequestParam(name = "isUpdate") Boolean update, @RequestParam(name = "playerId") int playerId, @RequestParam(name = "roster") int rosterId,@RequestParam(name = "search") String search, @RequestParam(name = "team") String team, @RequestParam(name = "leagueId") String leagueId, Model model) {
        Team teamFilter = teamDao.findTeamByFullName(team);
        List<Player> players = playerDao.findByTeamId(teamFilter);

        League league = leagueDao.getById(Integer.parseInt(leagueId));

        Roster roster = rosterDao.getById(rosterId);
        Player player = playerDao.getById((long) playerId);
        List<RosterPlayer> rosterPlayers = rosterPlayerDao.getByRosterId(roster);

        int activeCount = 0;
        int valueTotal = 0;

        for (RosterPlayer rp1 : rosterPlayers){

            if(rp1.getIsActive()){
                activeCount += 1;
            }

            valueTotal += rp1.getPlayerId().getPrice();

        }


        if (!search.equals("")){
            List<Player> playersByName = searchName(search);
            playersByName.addAll(players);
            players = playersByName;
        }

        boolean alreadyAdded = rosterPlayerDao.getRosterPlayerByRosterIdAndPlayerId(roster, player) != null;
        boolean lessThanTen = rosterPlayerDao.getByRosterId(roster).size() < 10;
        RosterPlayer rosterPlayer = rosterPlayerDao.getRosterPlayerByRosterIdAndPlayerId(roster, player);
        if(playerId != 0 && rosterPlayer != null){
            System.out.println(roster);
            System.out.println(player);
        }else{

            rosterPlayer = new RosterPlayer(0,player, roster, false);

        }

        System.out.println(rosterPlayer);


        if (playerId != 0 && !update && !alreadyAdded && lessThanTen && valueTotal + player.getPrice() <= Integer.parseInt(league.getLeagueDifficulty())){
            rosterPlayerDao.save(rosterPlayer);
        } else if(playerId != 0 && update && activeCount < 5 || rosterPlayer.getIsActive()){

            rosterPlayer.setIsActive(!rosterPlayer.getIsActive());

            rosterPlayerDao.save(rosterPlayer);
        }

        if (delete){

            rosterPlayerDao.delete(rosterPlayer);

        }

        List<Team> allTeams = teamDao.findAll();
        List<RosterPlayer> rosterPlayers1 = rosterPlayerDao.getByRosterId(roster);
        model.addAttribute("search", search);
        model.addAttribute("team", team);
        model.addAttribute("players", players);
        model.addAttribute("teams", allTeams);
        model.addAttribute("games", gm.getTodaysGames());
        model.addAttribute("roster", roster);
        model.addAttribute("leagueId", leagueId);
        model.addAttribute("rosterPlayers", rosterPlayers1);
        model.addAttribute("valueTotal", valueTotal);
        model.addAttribute("league", league);

        return "createTeam";
    }

    @RequestMapping(value = "/team/create/newRoster", method = RequestMethod.POST)
    public String getPlayerByName2(@RequestParam(name = "search") String search, @RequestParam(name = "rosterName") String rosterName, @RequestParam(name = "team") String team, @RequestParam(name = "leagueId") String leagueId, Model model) {
        Team teamFilter = teamDao.findTeamByFullName(team);
        List<Player> players = playerDao.findByTeamId(teamFilter);

        League league = leagueDao.getById(Integer.parseInt(leagueId));
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.getById(((User)user).getId());
        Optional<Roster> userRoster = rosterDao.getRosterByLeagueIdAndUserId(league, currentUser);


        if(userRoster.isEmpty()){

            Roster roster = new Roster(0, rosterName, league, currentUser);
            rosterDao.save(roster);
            userRoster = rosterDao.getRosterByLeagueIdAndUserId(league, currentUser);

        }
        if (!search.equals("")){
            List<Player> playersByName = searchName(search);
            playersByName.addAll(players);
            players = playersByName;
        }

        List<Team> allTeams = teamDao.findAll();
        model.addAttribute("players", players);
        model.addAttribute("teams", allTeams);
        model.addAttribute("games", gm.getTodaysGames());
        model.addAttribute("roster", userRoster.get());
        model.addAttribute("leagueId", leagueId);
        model.addAttribute("league", league);
        return "createTeam";
    }

    private List<Player> searchName(String nameToSearch){

        String[] splitName = nameToSearch.split(" ");

        if(splitName.length != 1){
            return playerDao.findPlayerByFirstNameAndLastName(splitName[0], splitName[1]);

        } else{

            return playerDao.findByFirstNameOrLastName(nameToSearch);
        }

    }
}
