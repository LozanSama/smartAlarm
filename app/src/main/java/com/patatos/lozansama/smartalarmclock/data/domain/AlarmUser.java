package com.patatos.lozansama.smartalarmclock.data.domain;


import java.io.Serializable;

public class AlarmUser implements Serializable {

    private int id;
    private String name;
    private int hour;
    private int minute;
    private boolean temperature;
    private boolean isTurnOn;

    public AlarmUser() {
    }

    public AlarmUser(int id, String name, int hour, int minute, boolean temperature, boolean isTurnOn) {
        this.id = id;
        this.name = name;
        this.hour = hour;
        this.minute = minute;
        this.temperature = temperature;
        this.isTurnOn = isTurnOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public boolean getTemperature() {
        return temperature;
    }

    public void setTemperature(boolean temperature) {
        this.temperature = temperature;
    }

    public boolean isTurnOn() {
        return isTurnOn;
    }

    public void setTurnOn(boolean turnOn) {
        isTurnOn = turnOn;
    }
}
