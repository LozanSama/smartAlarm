package com.patatos.lozansama.smartalarmclock.util;


import com.patatos.lozansama.smartalarmclock.data.domain.AlarmUser;
import com.patatos.lozansama.smartalarmclock.data.domain.User;
import com.patatos.lozansama.smartalarmclock.data.dto.AlarmsUserR;
import com.patatos.lozansama.smartalarmclock.data.dto.UserR;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class RealmUtil {

    public static void addToRealm(Realm realm, String email, RealmList<AlarmsUserR> realmList) {
        if (realm.isEmpty()) {
            realm.beginTransaction();
            UserR userRealm = realm.createObject(UserR.class);
            userRealm.setUserName(email);
            userRealm.setListAlarmsUsers(realmList);
            realm.commitTransaction();
        }
    }

    public static void addAlarmToRealm(Realm realm, AlarmUser alarmUser) {
        UserR userR = getUser(realm);
        realm.beginTransaction();
        userR.getListAlarmsUsers().add(new AlarmsUserR(userR.getListAlarmsUsers().size(),
                alarmUser.getName(), alarmUser.getHour(), alarmUser.getMinute(),
                alarmUser.getTemperature(), alarmUser.isTurnOn()));
        realm.commitTransaction();
    }


    public static void editAlarmInRealm(Realm realm, int position, AlarmUser newAlarmUser) {
        UserR userR = getUser(realm);

        AlarmsUserR newAlarmUserRealm = passAlarmToRealm(realm, newAlarmUser);
        deletedFromRealm(realm, position);

        realm.beginTransaction();
        userR.getListAlarmsUsers().add(newAlarmUserRealm);
        userR.getListAlarmsUsers().sort("id");
        realm.commitTransaction();
    }

    public static AlarmsUserR passAlarmToRealm(Realm realm, AlarmUser alarmUser) {
        UserR userR = getUser(realm);
        realm.beginTransaction();
        AlarmsUserR alarmsUserR = realm.createObject(AlarmsUserR.class);
        alarmsUserR.setId(userR.getListAlarmsUsers().size());
        alarmsUserR.setName(alarmUser.getName());
        alarmsUserR.setHour(alarmUser.getHour());
        alarmsUserR.setMinute(alarmUser.getMinute());
        alarmsUserR.setTemperature(alarmUser.getTemperature());
        alarmsUserR.setTurnOn(alarmUser.isTurnOn());
        realm.commitTransaction();
        return alarmsUserR;
    }

    public static void deletedFromRealm(Realm realm, int position) {
        UserR userR = getUser(realm);
        realm.beginTransaction();
        userR.getListAlarmsUsers().remove(position);
        realm.commitTransaction();
    }

    public static UserR getUser(Realm realm) {
        realm.beginTransaction();
        UserR userRealm = realm.where(UserR.class).findFirst();
        realm.commitTransaction();
        return userRealm;
    }

    public static List<AlarmUser> getNewList() {
        List<AlarmUser> alarmUsers = new ArrayList<>();
        Realm realm = Realm.getDefaultInstance();
        UserR userR = getUser(realm);
        for (AlarmsUserR a : userR.getListAlarmsUsers()
                ) {
            alarmUsers.add(new AlarmUser(a.getId(), a.getName(), a.getHour(), a.getMinute(), a.getTemperature(), a.isTurnOn()));
        }
        return alarmUsers;
    }

    public static User passToDomain(UserR userRealm) {
        List<AlarmUser> domainList = getNewList();

        User userDomain = new User(userRealm.getUserName(), domainList);
        return userDomain;
    }

    public static void upgradeListRealm(Realm realm, List<AlarmUser> listAlarm) {
        UserR user = getUser(realm);
        for (AlarmUser a : listAlarm
                ) {
            realm.beginTransaction();
            AlarmsUserR alarmsUserR = realm.createObject(AlarmsUserR.class);
            alarmsUserR.setId(user.getListAlarmsUsers().size());
            alarmsUserR.setName(a.getName());
            alarmsUserR.setHour(a.getHour());
            alarmsUserR.setMinute(a.getMinute());
            alarmsUserR.setTemperature(a.getTemperature());
            alarmsUserR.setTurnOn(a.isTurnOn());
            user.getListAlarmsUsers().add(alarmsUserR);
            realm.commitTransaction();
        }
    }


}
