package com.patatos.lozansama.smartalarmclock.data.dto;

import io.realm.RealmObject;

public class AlarmsUser extends RealmObject {

    String name;
    String hour;
    String days;
    boolean isTurnOn;

    public AlarmsUser() {
    }

    public AlarmsUser(String name, String hour, String days, boolean isTurnOn) {
        this.name = name;
        this.hour = hour;
        this.days = days;
        this.isTurnOn = isTurnOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public boolean isTurnOn() {
        return isTurnOn;
    }

    public void setTurnOn(boolean turnOn) {
        isTurnOn = turnOn;
    }
}
