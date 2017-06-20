package com.patatos.lozansama.smartalarmclock.data.domain;


public class AlarmUser {
    private String name;
    private String hour;
    private boolean temperature;
    private boolean isTurnOn;

    public AlarmUser() {
    }

    public AlarmUser(String name, String hour, boolean temperature, boolean isTurnOn) {
        this.name = name;
        this.hour = hour;
        this.temperature = temperature;
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
