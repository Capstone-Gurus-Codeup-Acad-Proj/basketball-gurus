package com.example.basketballgurus.models;

public class Team {

    private int id;

    private String fullName;

    private String city;

    private String nickname;

    private String abbr;

    private String logoUrl;

    public Team(int id, String fullName, String city, String nickname, String abbr, String logo_url) {
        this.id = id;
        this.fullName = fullName;
        this.city = city;
        this.nickname = nickname;
        this.abbr = abbr;
        this.logoUrl = logo_url;
    }

    public Team() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
