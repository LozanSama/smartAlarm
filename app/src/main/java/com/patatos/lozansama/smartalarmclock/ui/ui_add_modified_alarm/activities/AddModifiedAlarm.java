package com.patatos.lozansama.smartalarmclock.ui.ui_add_modified_alarm.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;

import com.patatos.lozansama.smartalarmclock.R;
import com.patatos.lozansama.smartalarmclock.data.domain.AlarmUser;
import com.patatos.lozansama.smartalarmclock.ui.ui_alarm_list.activities.AlarmList;
import com.patatos.lozansama.smartalarmclock.util.RealmUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import io.realm.Realm;

public class AddModifiedAlarm extends AppCompatActivity {

    final Realm realm = Realm.getDefaultInstance();

    boolean isEditing = false;
    AlarmUser alarmUser;
    Intent intent;
    int position;

    @BindView(R.id.et_name_alarm)
    EditText etNameAlarm;
    @BindView(R.id.switch_temperature)
    Switch switchTemperature;
    @BindView(R.id.tp_hour)
    TimePicker tpHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_modified_alarm_activity_layout);
        ButterKnife.bind(this);

        intent = new Intent(this, AlarmList.class);
        position = getIntent().getIntExtra("position", 0);
        alarmUser = (AlarmUser) getIntent().getSerializableExtra("clickedAlarm");
        if (alarmUser != null) {
            isEditing = true;
            etNameAlarm.setText(alarmUser.getName());
            switchTemperature.setChecked(alarmUser.getTemperature());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tpHour.setHour(alarmUser.getHour());
                tpHour.setMinute(alarmUser.getMinute());
            }
        }
    }

    @OnTouch(R.id.et_name_alarm)
    public boolean onTouch() {
        etNameAlarm.setFocusable(true);
        etNameAlarm.setFocusableInTouchMode(true);
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(intent);
    }

    @OnClick(R.id.fb_set_alarm)
    public void onClickFb() {
        if (isEditing) {
            AlarmUser newAlarmUser = new AlarmUser(alarmUser.getId(), etNameAlarm.getText().toString(),
                    tpHour.getHour(), tpHour.getMinute(), switchTemperature.isChecked(), true);

            RealmUtil.editAlarmInRealm(realm, position, newAlarmUser);
            startActivity(intent);
            finish();
        } else {
            AlarmUser alarmUser = new AlarmUser(RealmUtil.getNewList().size(), etNameAlarm.getText().toString(),
                    tpHour.getHour(), tpHour.getMinute(), switchTemperature.isChecked(), true);

            RealmUtil.addAlarmToRealm(realm, alarmUser);
            startActivity(intent);
            finish();
        }
    }
}
