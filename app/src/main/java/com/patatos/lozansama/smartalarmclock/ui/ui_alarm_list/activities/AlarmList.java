package com.patatos.lozansama.smartalarmclock.ui.ui_alarm_list.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.patatos.lozansama.smartalarmclock.R;
import com.patatos.lozansama.smartalarmclock.data.dto.User;

import io.realm.Realm;

public class AlarmList extends AppCompatActivity {

    final Realm realm = Realm.getDefaultInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_list_activity_layout);

        User user = realm.where(User.class).findFirst();
        if (user.getListAlarmsUsers().isEmpty()) {

        }

    }
}
