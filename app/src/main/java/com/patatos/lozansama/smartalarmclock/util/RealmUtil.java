package com.patatos.lozansama.smartalarmclock.util;


import com.patatos.lozansama.smartalarmclock.data.dto.User;

import io.realm.Realm;

public class RealmUtil {

    public static void addToRealm(Realm realm, String email, String password) {
        if (realm.isEmpty()) {
            realm.beginTransaction();
            User userRealm = realm.createObject(User.class);
            userRealm.setUserName(email);
            userRealm.setPassword(password);
            userRealm.setListAlarmsUsers(null);

            realm.commitTransaction();
        }
    }
}
