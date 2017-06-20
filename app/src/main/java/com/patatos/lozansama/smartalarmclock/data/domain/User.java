package com.patatos.lozansama.smartalarmclock.data.domain;

import java.util.List;

public class User {
    private String userName;
    private List<AlarmUser> listAlarm;

    public User() {
    }

    public User(String userName, List<AlarmUser> listAlarm) {
        this.userName = userName;
        this.listAlarm = listAlarm;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<AlarmUser> getListAlarm() {
        return listAlarm;
    }

    public void setListAlarm(List<AlarmUser> listAlarm) {
        this.listAlarm = listAlarm;
    }
}
