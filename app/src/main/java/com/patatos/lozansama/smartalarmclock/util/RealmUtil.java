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
            userRealm.setListAlarmsUsers(null);
            realm.commitTransaction();
        }
    }

    public static User passToDomain(UserR userRealm) {
        List<AlarmUser> domainList = new ArrayList<>();
        for (AlarmsUserR a : userRealm.getListAlarmsUsers()
                ) {
            domainList.add(new AlarmUser(a.getName(), a.getHour(), a.getTemperature(), a.isTurnOn()));
        }

        User userDomain = new User(userRealm.getUserName(), domainList);
        return userDomain;
    }

    public static UserR passToRealm(User user){
        RealmList<AlarmsUserR> realmList = new RealmList<>();
        for (AlarmUser a : user.getListAlarm()
                ) {
            realmList.add(new AlarmsUserR(a.getName(), a.getHour(), a.getTemperature(), a.isTurnOn()));
        }

        UserR userRealm = new UserR(user.getUserName(), realmList);
        return userRealm;
    }
}
