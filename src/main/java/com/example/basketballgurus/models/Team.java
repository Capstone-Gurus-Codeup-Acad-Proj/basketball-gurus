package com.example.basketballgurus.models;

public class Team {

    private int id;

    private String full_name;

    private String city;

    private String nickname;

    private String abbr;

    private String logo_url;

    public Team(int id, String full_name, String city, String nickname, String abbr, String logo_url) {
        this.id = id;
        this.full_name = full_name;
        this.city = city;
        this.nickname = nickname;
        this.abbr = abbr;
        this.logo_url = logo_url;
    }

    public Team() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }
}
