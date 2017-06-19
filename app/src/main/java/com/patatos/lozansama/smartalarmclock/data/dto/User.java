package com.patatos.lozansama.smartalarmclock.data.dto;

import io.realm.RealmList;
import io.realm.RealmObject;

public class User extends RealmObject {

    private String userName;
    private String password;
    private RealmList<AlarmsUser> listAlarmsUsers;

    public User() {
    }

    public User(String userName, String password, RealmList<AlarmsUser> listAlarmsUsers) {
        this.userName = userName;
        this.password = password;
        this.listAlarmsUsers = listAlarmsUsers;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RealmList<AlarmsUser> getListAlarmsUsers() {
        return listAlarmsUsers;
    }

    public void setListAlarmsUsers(RealmList<AlarmsUser> listAlarmsUsers) {
        this.listAlarmsUsers = listAlarmsUsers;
    }
}
