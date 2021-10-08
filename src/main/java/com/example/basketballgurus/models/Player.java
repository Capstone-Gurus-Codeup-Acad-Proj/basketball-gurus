package com.example.basketballgurus.models;

import java.util.Date;

public class Player {
    private int id;
    private String firstName;
    private String lastName;
    private String teamName;
    private int teamId;
    private String position;
    private Date dob;
    private int height;
    private int weight;
    private int jerseyNumber;
    private int age;
    private double point;
    private double blocks;
    private double assists;
    private double rebounds;
    private double turnOvers;
    private float percentThrees;
    private float percentFreethrows;
    private String headshotUrl;
    private int price;

    public Player(){
    }
    public Player(int id, String firstName, String lastName, String teamName, int teamId, String position, Date dob, int height, int weight, int jerseyNumber, int age, double point, double blocks, double assists, double rebounds, double turnOvers, int percentThrees, int percentFreethrows, String headshotUrl, int price) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teamName = teamName;
        this.teamId = teamId;
        this.position = position;
        this.dob = dob;
        this.height = height;
        this.weight = weight;
        this.jerseyNumber = jerseyNumber;
        this.age = age;
        this.point = point;
        this.blocks = blocks;
        this.assists = assists;
        this.rebounds = rebounds;
        this.turnOvers = turnOvers;
        this.percentThrees = percentThrees;
        this.percentFreethrows = percentFreethrows;
        this.headshotUrl = headshotUrl;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public double getBlocks() {
        return blocks;
    }

    public void setBlocks(double blocks) {
        this.blocks = blocks;
    }

    public double getAssists() {
        return assists;
    }

    public void setAssists(double assists) {
        this.assists = assists;
    }

    public double getRebounds() {
        return rebounds;
    }

    public void setRebounds(double rebounds) {
        this.rebounds = rebounds;
    }

    public double getTurnOvers() {
        return turnOvers;
    }

    public void setTurnOvers(double turnOvers) {
        this.turnOvers = turnOvers;
    }

    public float getPercentThrees() {
        return percentThrees;
    }

    public void setPercentThrees(int percentThrees) {
        this.percentThrees = percentThrees;
    }

    public float getPercentFreethrows() {
        return percentFreethrows;
    }

    public void setPercentFreethrows(int percentFreethrows) {
        this.percentFreethrows = percentFreethrows;
    }

    public String getHeadshotUrl() {
        return headshotUrl;
    }

    public void setHeadshotUrl(String headshotUrl) {
        this.headshotUrl = headshotUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
