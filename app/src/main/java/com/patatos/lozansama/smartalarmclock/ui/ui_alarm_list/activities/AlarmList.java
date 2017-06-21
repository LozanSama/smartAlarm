package com.patatos.lozansama.smartalarmclock.ui.ui_alarm_list.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.patatos.lozansama.smartalarmclock.R;
import com.patatos.lozansama.smartalarmclock.data.domain.AlarmUser;
import com.patatos.lozansama.smartalarmclock.data.domain.User;
import com.patatos.lozansama.smartalarmclock.ui.ui_add_modified_alarm.activities.AddModifiedAlarm;
import com.patatos.lozansama.smartalarmclock.ui.ui_alarm_list.OnItemClickInterface;
import com.patatos.lozansama.smartalarmclock.ui.ui_alarm_list.adapters.AlarmAdapter;
import com.patatos.lozansama.smartalarmclock.util.AlarmReceiver;
import com.patatos.lozansama.smartalarmclock.util.RealmUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class AlarmList extends AppCompatActivity implements OnItemClickInterface {

    final Realm realm = Realm.getDefaultInstance();
    User userFromFB;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    AlarmManager alarmManager;
    Calendar calendar;
    PendingIntent pendingIntent;

    @BindView(R.id.rv_item_list)
    RecyclerView rvItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_list_activity_layout);
        ButterKnife.bind(this);

        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        if (RealmUtil.getUser(realm).getListAlarmsUsers().isEmpty()) {
            addDataFromFirebase();
        } else {
            addDataFromRealm();

        }

    }

    private void addDataFromFirebase() {
        final List<AlarmUser> data = new ArrayList<>();
        databaseReference.child(RealmUtil.getUser(realm).getUserName().replace(".", "")).child("listAlarm").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    AlarmUser post = postSnapshot.getValue(AlarmUser.class);
                    data.add(post);
                }
                RealmUtil.upgradeListRealm(realm, data);

                addDataFromRealm();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: ", databaseError.getMessage());
            }
        });
    }

    public void setAlarmsOn(){
       setPendingItem();
        for (AlarmUser a:  userFromFB.getListAlarm()
             ) {
            if (a.isTurnOn()) {
                calendar.set(Calendar.HOUR, a.getHour());
                calendar.set(Calendar.MINUTE, a.getMinute());
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()
                    , pendingIntent);
            }
        }

    }

    private void setPendingItem() {
        Intent intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void addDataFromRealm() {
        userFromFB = RealmUtil.passToDomain(RealmUtil.getUser(realm));

        AlarmAdapter alarmAdapter = new AlarmAdapter(userFromFB.getListAlarm(), this);
        rvItemList.setAdapter(alarmAdapter);
        rvItemList.setHasFixedSize(true);

        setAlarmsOn();
    }

    @OnClick(R.id.fb_add_alarm)
    public void onClick() {
        Intent intent = new Intent(this, AddModifiedAlarm.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onItemClick(AlarmUser alarmUser, int position) {
        Intent intent = new Intent(this, AddModifiedAlarm.class);
        intent.putExtra("position", position);
        intent.putExtra("clickedAlarm", alarmUser);
        startActivity(intent);
        finish();
    }

    @Override
    public void onItemLongClick(int position) {
        RealmUtil.deletedFromRealm(realm, position);
    }

    @Override
    public void onSwitchChanged(AlarmUser alarmUser, int position) {
        RealmUtil.editAlarmInRealm(realm, position, alarmUser);
        turnOffAlarm(alarmUser);
    }

    private void turnOffAlarm(AlarmUser alarmUser) {
        setPendingItem();
        calendar.set(Calendar.HOUR, alarmUser.getHour());
        calendar.set(Calendar.MINUTE, alarmUser.getMinute());
        alarmManager.cancel(pendingIntent);
        setAlarmsOn();
    }

    @Override
    protected void onStop() {
        super.onStop();
        databaseReference.child(userFromFB.getUserName().replace(".", "")).child("listAlarm").setValue(RealmUtil.getNewList());
    }
}
