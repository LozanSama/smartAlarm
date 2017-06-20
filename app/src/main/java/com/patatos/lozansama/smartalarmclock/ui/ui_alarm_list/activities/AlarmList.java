package com.patatos.lozansama.smartalarmclock.ui.ui_alarm_list.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.patatos.lozansama.smartalarmclock.R;
import com.patatos.lozansama.smartalarmclock.data.domain.User;
import com.patatos.lozansama.smartalarmclock.data.dto.UserR;
import com.patatos.lozansama.smartalarmclock.ui.ui_add_modified_alarm.activities.AddModifiedAlarm;
import com.patatos.lozansama.smartalarmclock.util.RealmUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class AlarmList extends AppCompatActivity {

    final Realm realm = Realm.getDefaultInstance();
    User userFromFB;
    UserR userRealm;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_list_activity_layout);
        ButterKnife.bind(this);

        userRealm = realm.where(UserR.class).findFirst();

        //databaseReference.child("users").push().setValue(userFromFB);

        if (userRealm.getListAlarmsUsers().isEmpty()) {
            databaseReference.child("users").equalTo(userRealm.getUserName(), "userName").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    userFromFB = (User) dataSnapshot.getValue();

                    if (userFromFB.getListAlarm().isEmpty()) {
                        return;
                    } else {
                        userRealm = RealmUtil.passToRealm(userFromFB);
                        realm.deleteAll();
                        RealmUtil.addToRealm(realm, userRealm.getUserName(), userRealm.getListAlarmsUsers());
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    userFromFB = dataSnapshot.getValue(User.class);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    userFromFB = dataSnapshot.getValue(User.class);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    userFromFB = dataSnapshot.getValue(User.class);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @OnClick(R.id.fb_add_alarm)
    public void onClick() {
        Intent intent = new Intent(this, AddModifiedAlarm.class);
        startActivity(intent);
    }
}
