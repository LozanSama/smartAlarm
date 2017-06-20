package com.patatos.lozansama.smartalarmclock.data.dto;


import io.realm.RealmList;
import io.realm.RealmObject;

public class UserR extends RealmObject {

    private String userName;
    private RealmList<AlarmsUserR> listAlarmsUsers;

    public UserR() {
    }

    public UserR(String userName, RealmList<AlarmsUserR> listAlarmsUsers) {
        this.userName = userName;
        this.listAlarmsUsers = listAlarmsUsers;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public RealmList<AlarmsUserR> getListAlarmsUsers() {
        return listAlarmsUsers;
    }

    public void setListAlarmsUsers(RealmList<AlarmsUserR> listAlarmsUsers) {
        this.listAlarmsUsers = listAlarmsUsers;
    }
}
