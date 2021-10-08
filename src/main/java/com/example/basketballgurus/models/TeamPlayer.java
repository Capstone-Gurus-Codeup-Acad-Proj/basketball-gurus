package com.example.basketballgurus.models;

public class TeamPlayer {

    private int id;
    private int playerId;
    private int teamId;
    private Boolean isActive;

    public TeamPlayer() {
    }

    public TeamPlayer(int id, int player_id, int team_id, Boolean isActive) {
        this.id = id;
        this.playerId = player_id;
        this.teamId = team_id;
        this.isActive = isActive;
    }



        public int getId () {
            return id;
        }

        public void setId ( int id){
            this.id = id;
        }

        public int getPlayer_id () {
            return playerId;
        }

        public void setPlayer_id ( int player_id){
            this.playerId = player_id;
        }

        public int getTeamId() {
            return teamId;
        }

        public void setTeamId(int teamId){
            this.teamId = teamId;
        }

        public Boolean getActive () {
            return isActive;
        }

        public void setActive (Boolean active){
            isActive = active;
        }

}