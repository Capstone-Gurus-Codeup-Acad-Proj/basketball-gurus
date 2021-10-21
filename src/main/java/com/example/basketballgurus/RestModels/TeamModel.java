package com.example.basketballgurus.RestModels;

import lombok.Getter;
import lombok.Setter;

public class TeamModel {

    @Getter @Setter
    public int teamId;
    @Getter @Setter
    public String shortName;
    @Getter @Setter
    public String fullName;
    @Getter @Setter
    public String nickName;
    @Getter @Setter
    public String logo;

}
